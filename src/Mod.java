import java.util.ArrayList;
import java.util.List;

/**
 * @author Varun
 * Data structure used to save input and intermediate results
 */
public class Mod {
	boolean notCombined = true;
	String bitString;
	List<Long> minTerm = new ArrayList<Long>();
	Mod(long l, int length) {
		this.minTerm.add(l);
		this.bitString = Long.toBinaryString(l);
		{
			StringBuilder b = new StringBuilder();
			for (int i = 0; i < length - bitString.length(); i++) {
				b.append(0);
			}
			b.append(bitString);
			bitString = b.toString();
		}
	}
	Mod(String bitString, List l1, List l2) {
		this.bitString = bitString;
		minTerm.addAll(l1);
		minTerm.addAll(l2);
	}

	@Override
	public String toString() {
		minTerm.sort(null);
		return minTerm.toString() + " : " + bitString;
	}
	
	@Override
	public boolean equals(Object obj){
		Mod t = (Mod)obj;
		minTerm.sort(null);
		t.minTerm.sort(null);
		return t.minTerm.toString().equals(minTerm.toString());
	}
	
	@Override
	public int hashCode() {
		minTerm.sort(null);
		return minTerm.hashCode();
	}
}
