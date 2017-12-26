import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Varun
 * Implementation of the Quine-McCluskey Algorithm in 2 steps methods : algoStep1() and algoStep2()
 * 
 */
public class Algorithm {

	static List<Mod> algo(Map<Long, List<Mod>> minTerms, List<Mod> allTerms) {
		Set<Mod> step1Result = new HashSet<Mod>(algoStep1(allTerms));
		/*step1Result.forEach(System.out::println);
		System.out.println();*/
		
		List<Mod> result = algoStep2(minTerms, step1Result);
		return result;
	}

	private static List<Mod> algoStep2(Map<Long, List<Mod>> minTerms, Set<Mod> step1Result) {
		Set<Mod> result = new HashSet<Mod>();
		Set<Long> keys = new HashSet<Long>(minTerms.keySet());
		step1Result.forEach(e -> {e.minTerm.forEach(b -> {if(minTerms.containsKey(b)) minTerms.get(b).add(e);});});
		//minTerms.forEach((k,v) -> System.out.println(k+" : "+v));
		//System.out.println();
		//res.forEach(e -> {e.minTerm.forEach(b -> {System.out.println(b);;});});
		minTerms.forEach((k,v) -> {if(v.size() == 1) {/*System.out.print(k+",");*/result.addAll(v);v.forEach(r -> {keys.removeAll(r.minTerm);});}});
		//keys.forEach(System.out::println);
		List<Mod> remaining = new ArrayList<Mod>();
		keys.forEach(k -> {remaining.addAll(minTerms.get(k));});
		remaining.sort(new Comparator<Mod>()  {
			@Override	
			public int compare(Mod o1, Mod o2) {
				long c1 = keys.stream().filter(k -> o1.minTerm.contains(k)).count();
				long c2 = keys.stream().filter(k -> o2.minTerm.contains(k)).count();
				return (int)(c2 - c1);
			}
		});
		remaining.forEach(e -> {if(!Collections.disjoint(keys, e.minTerm)){result.add(e); keys.removeAll(e.minTerm);}});
		//System.out.println(remaining);
		//System.out.println(result);
		return new ArrayList<>(result);
	}

	private static List<Mod> algoStep1(List<Mod> allTerms) {
		List<Mod> result = new ArrayList<Mod>();
		while(true) {
			List<Mod> interResult = new ArrayList<Mod>();
			for (int i = 0; i < allTerms.size(); i++) {
				for (int j = i+1; j < allTerms.size(); j++) {
					Mod combined = combineIndex(allTerms.get(i), allTerms.get(j));
					if(null != combined)	interResult.add(combined);
				}
				if (allTerms.get(i).notCombined) result.add(allTerms.get(i));
			}
			if (interResult.isEmpty()) {
				break;
			}
			allTerms = interResult;
		}
		return result;
	}

	private static Mod combineIndex(Mod mod1, Mod mod2) {
		int r = -1;
		for (int i = 0; i < mod2.bitString.length(); i++) {
			if (mod1.bitString.charAt(i) != mod2.bitString.charAt(i)) {
	              if(r == -1)	r = i;
	              else		return null;
	          }
		}
		if (r == -1)		return null;
		mod1.notCombined = false;
		mod2.notCombined = false;
		String re = mod1.bitString.substring(0,r)+'-';
		if (++r!=mod1.bitString.length()) re +=  mod1.bitString.substring(r);
		Mod res = new Mod(re, mod1.minTerm, mod2.minTerm);
		return res;
	}

}
