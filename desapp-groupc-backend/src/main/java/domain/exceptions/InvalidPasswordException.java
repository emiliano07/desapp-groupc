package domain.exceptions;

public class InvalidPasswordException extends Exception {

	public InvalidPasswordException(){
		super("The password is invalid. Please choose one between 4 to 12 characters");
	}
}