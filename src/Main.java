import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Varun
 * Quine-McCluskey Implementation
 * input description within input file located in src folder
 * Under guidance of Professor A. Daboli
 * Algorithm reference:	https://en.wikipedia.org/wiki/Quine%E2%80%93McCluskey_algorithm
 * 
 */
public class Main {

	private static final String REL_PATH = "./input/";
	private static final String FILE_NAME_WITH_EXTENTION = "input.txt";
	private static final String INPUT_PATH = REL_PATH + FILE_NAME_WITH_EXTENTION;

	public static void main(String[] args) throws FileNotFoundException {
		int variables;
		Map<Long, List<Mod>> minTerms = new HashMap<Long, List<Mod>>();
		List<Mod> dontCare = new ArrayList<>();
		List<Mod> allTerms = new ArrayList<>();
		variables = input(minTerms, dontCare, allTerms);
		
		List<Mod> result = Algorithm.algo(minTerms, allTerms);
		print(variables, result);
	}

	private static void print(int variables, List<Mod> result) {
		final char A = 'A';
		System.out.print("f(");
		for (int i = 0; i < variables; i++) {
			System.out.print((char)(A+i));
			if (i != variables-1) System.out.print(",");
		}
		System.out.print(") = ");
		for (Mod mod : result) {
			for (int i = 0; i < variables; i++) {
				char t = mod.bitString.charAt(i);
				if (t == '-') continue;
				else {
					System.out.print((char)(A+i));
					if (t == '0')	System.out.print("'");
				}
			}
			if(result.indexOf(mod) != result.size()-1)	System.out.print(" + ");
		}
	}

	private static int input(Map<Long, List<Mod>> minTerms, List<Mod> dontCare, List<Mod> allTerms) throws FileNotFoundException {
		int variables;
		Scanner in = new Scanner(new File(INPUT_PATH));
		variables = in.nextInt();in.nextLine();
		{
			int nMinTerms = in.nextInt();in.nextLine();
			int nDontCare = in.nextInt();in.nextLine();
			while (nMinTerms-- > 0) {
				long temp = in.nextLong();
				Mod mod = new Mod(temp, variables);
				allTerms.add(mod);
				minTerms.put(temp, new ArrayList<Mod>());
			}
			in.nextLine();
			while (nDontCare-- > 0) {
				Mod mod = new Mod(in.nextLong(), variables);
				allTerms.add(mod);
				dontCare.add(mod);
			}
		}
		in.close();
		return variables;
	}

}
