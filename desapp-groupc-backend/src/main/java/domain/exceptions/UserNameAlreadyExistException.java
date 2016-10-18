package domain.exceptions;

public class UserNameAlreadyExistException extends Exception {

	public UserNameAlreadyExistException(){
		super("The user name already exist. Please choose another");
	}
}