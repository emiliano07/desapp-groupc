package domain.exceptions;

public class UserNotExistException extends Exception {

	public UserNotExistException(){
		super("The user not exist. Please put another");
	}
}