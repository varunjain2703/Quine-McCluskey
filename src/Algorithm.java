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
		
		return new ArrayList<>(result);
	}

	private static List<Mod> algoStep1(List<Mod> allTerms) {
		List<Mod> result = new ArrayList<Mod>();
		
		return result;
	}

	private static Mod combine(Mod mod1, Mod mod2) {
		int r = -1;
		
		Mod res = new Mod(re, mod1.minTerm, mod2.minTerm);
		return res;
	}

}
