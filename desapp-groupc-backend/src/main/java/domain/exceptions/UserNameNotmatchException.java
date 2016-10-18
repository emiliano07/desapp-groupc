package domain.exceptions;

public class UserNameNotmatchException extends Exception {

	public UserNameNotmatchException(){
		super("The user name not exist. Please put another");
	}
}