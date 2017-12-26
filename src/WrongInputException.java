
public class WrongInputException extends Exception{

	StringBuilder message;
	WrongInputException(long term, int variables, long max) {
		message = new StringBuilder("Error:Wrong Input ");
		message.append(term);
		message.append("\nReason: For ");
		message.append(variables);
		message.append(" variables terms should be in range (0,");
		message.append(max);
		message.append(")");
	}
}
