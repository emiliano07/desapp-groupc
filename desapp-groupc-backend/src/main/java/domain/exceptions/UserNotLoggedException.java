package domain.exceptions;

public class UserNotLoggedException extends Exception {

	public UserNotLoggedException(){
		super("The user is not logged. Please login");
	}
}