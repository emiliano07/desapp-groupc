package domain.exceptions;

public class PasswordNotmatchException extends Exception {

	public PasswordNotmatchException(){
		super("The password not exist. Please put another");
	}
}