import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GUI_Input implements ActionListener{

	private static Map<Long, List<Mod>> minTerms = new HashMap<Long, List<Mod>>();
	private static List<Mod> allTerms = new ArrayList<>();
	
	JTextField variableT, minTermsT, dontCareT;
	JLabel result;
	JButton submit;
	
	GUI_Input() {
		JFrame frame = new JFrame();
		submit = new JButton("Submit");
		submit.addActionListener(this);
		JLabel variableL, minTermsL, dontCareL;
		result = new JLabel();
		variableL = new JLabel("Number of variables");
		minTermsL = new JLabel("Minterms (comma seprated)");
		dontCareL = new JLabel("Dont care terms");
		
		variableT = new JTextField("4");
		minTermsT = new JTextField("4,8,10,11,12,15");
		dontCareT = new JTextField("9,14");
		
		variableL.setBounds(50, 50, 300, 30);
		minTermsL.setBounds(50, 100, 300, 30);
		dontCareL.setBounds(50, 150, 300, 30);

		variableT.setBounds(230, 50, 300, 30);
		minTermsT.setBounds(230, 100, 300, 30);
		dontCareT.setBounds(230, 150, 300, 30);
		
		submit.setBounds(180, 200, 100, 30);
		result.setBounds(150, 250, 600, 30);
		
		frame.add(variableL);
		frame.add(minTermsL);
		frame.add(dontCareL);
		frame.add(variableT);
		frame.add(minTermsT);
		frame.add(dontCareT);
		frame.add(submit);
		frame.add(result);
		
		frame.setSize(600, 350);
		frame.setLayout(null);  
		frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) { 
        try {
        	int variables = Integer.parseInt(variableT.getText());
        	input(variables, minTermsT.getText(), dontCareT.getText());
			result.setText(print(variables, Algorithm.algo(minTerms, allTerms)));
		} catch (WrongInputException e1) {
			String error = "<html>" + e1.message.toString().replace("\n", "<br>");
			result.setText(error);
		}
    }
	
	private static void input(int variables, String nMinTerms, String nDontCare) throws WrongInputException {
		minTerms = new HashMap<Long, List<Mod>>();
		allTerms = new ArrayList<>();
		long max = (long)Math.pow(2, variables) - 1;
		for (String t : nMinTerms.split(",")) {
			try {
				long term = Integer.parseInt(t);
				if (term > max || term < 0) throw new WrongInputException(term, variables, max);
				Mod mod = new Mod(term, variables);
				allTerms.add(mod);
				minTerms.put(term, new ArrayList<Mod>());
			} catch (NumberFormatException e) {	}
		}
		for (String t : nDontCare.split(",")) {
			try {
				long term = Integer.parseInt(t);
				if (term > max || term < 0) throw new WrongInputException(term, variables, max);
				Mod mod = new Mod(term, variables);
				allTerms.add(mod);
			} catch (NumberFormatException e) {	}
		}
	}

	private static String print(int variables, List<Mod> result) {
		final char A = 'A';
		StringBuilder print = new StringBuilder("f(");
		for (int i = 0; i < variables; i++) {
			print.append((char)(A+i));
			if (i != variables-1) print.append(",");
		}
		print.append(") = ");
		for (Mod mod : result) {
			for (int i = 0; i < variables; i++) {
				char t = mod.bitString.charAt(i);
				if (t == '-') continue;
				else {
					print.append((char)(A+i));
					if (t == '0')	print.append("'");
				}
			}
			if(result.indexOf(mod) != result.size()-1)	print.append(" + ");
		}
		return print.toString();
	}
	
}
