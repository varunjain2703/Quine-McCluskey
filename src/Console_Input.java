import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Console_Input {
	
	private static final String REL_PATH = "./input/";
	private static final String FILE_NAME_WITH_EXTENTION = "input.txt";
	private static final String INPUT_PATH = REL_PATH + FILE_NAME_WITH_EXTENTION;
	
	
	public static void fromConsole() {
		int variables;
		Map<Long, List<Mod>> minTerms = new HashMap<Long, List<Mod>>();
		List<Mod> dontCare = new ArrayList<>();
		List<Mod> allTerms = new ArrayList<>();
		try {
			variables = input(minTerms, dontCare, allTerms);
			List<Mod> result = Algorithm.algo(minTerms, allTerms);
			print(variables, result);
		} catch (FileNotFoundException e) {
			System.out.println("Error:File not found \"" + INPUT_PATH + "\"");
		} catch (WrongInputException e) {
			System.out.println(e.message);
		}
	}

	private static void print(int variables, List<Mod> result) {
		final char A = 'A';
		System.out.print("f(");
		for (int i = 0; i < variables; i++) {
			System.out.print((char) (A + i));
			if (i != variables - 1)
				System.out.print(",");
		}
		System.out.print(") = ");
		for (Mod mod : result) {
			for (int i = 0; i < variables; i++) {
				char t = mod.bitString.charAt(i);
				if (t == '-')
					continue;
				else {
					System.out.print((char) (A + i));
					if (t == '0')
						System.out.print("'");
				}
			}
			if (result.indexOf(mod) != result.size() - 1)
				System.out.print(" + ");
		}
	}

	private static int input(Map<Long, List<Mod>> minTerms, List<Mod> dontCare, List<Mod> allTerms)
			throws FileNotFoundException, WrongInputException {
		int variables;
		Scanner in = new Scanner(new File(INPUT_PATH));
		variables = in.nextInt();
		in.nextLine();
		long max = (long) Math.pow(2, variables) - 1;
		{
			int nMinTerms = in.nextInt();
			in.nextLine();
			int nDontCare = in.nextInt();
			in.nextLine();
			while (nMinTerms-- > 0) {
				long term = in.nextLong();
				if (term > max || term < 0) {
					/*
					 * System.out.println("Wrong Input:\t" + temp);
					 * System.out.println("Reason: For "
					 * +variables+" variables terms should be in range (0,"+max+
					 * ")"); return 0;
					 */
					throw new WrongInputException(term, variables, max);
				}
				Mod mod = new Mod(term, variables);
				allTerms.add(mod);
				minTerms.put(term, new ArrayList<Mod>());
			}
			in.nextLine();
			while (nDontCare-- > 0) {
				long term = in.nextLong();
				Mod mod = new Mod(term, variables);
				allTerms.add(mod);
				dontCare.add(mod);
			}
		}
		in.close();
		return variables;
	}

}
