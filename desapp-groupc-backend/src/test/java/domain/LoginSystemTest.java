package domain;

import org.junit.Assert;
import org.junit.Test;

import domain.User;
import domain.builders.UserBuilder;
import domain.exceptions.InvalidPasswordException;
import domain.exceptions.PasswordNotmatchException;
import domain.exceptions.UserNameAlreadyExistException;
import domain.exceptions.UserNameNotmatchException;
import domain.exceptions.UserNotExistException;
import domain.exceptions.UserNotLoggedException;

public class LoginSystemTest {
	
	@Test
    public void registerNewUserCorrectly() throws Exception{
		User user = UserBuilder.aUser().build();
		user.getSistem().registerNewUser("franciolucio", "1234", "franciolucio@gmail.com");
		Assert.assertEquals("franciolucio", user.getSistem().users.get(0).userName);
	}
	
	@Test
    public void registerNewUserIncorrectlyBecauseThereIsAlreadyAUserWithTheUsername() throws Exception{
		User user = UserBuilder.aUser().build();
		User userBis = UserBuilder.aUser().withUserName("franciolucio").build();
		user.getSistem().logSistem.users.put(userBis.userName, userBis.password);
		try {
			user.getSistem().registerNewUser("franciolucio", "1234", "franciolucio@gmail.com");
		}catch (UserNameAlreadyExistException e) {
			 System.out.println(e);
		}
	}
	
	@Test
    public void registerNewUserIncorrectlybecauseNoRegardPassword01() throws Exception{
		User user = UserBuilder.aUser().withPassword("123").build();
		try {
			user.getSistem().registerNewUser("franciolucio", "1234", "franciolucio@gmail.com");
			Assert.assertEquals("franciolucio", user.getSistem().users.get(0).userName);
		}catch (InvalidPasswordException e) {
			 System.out.println(e);
		}
	}
	
	@Test
    public void changePassword() throws Exception{
		User user = UserBuilder.aUser().withPassword("1234").build();
		user.getSistem().users.add(user);
		user.getSistem().logSistem.users.put("UserName", "1234");
		user.changePassword("UserName", "1234", "unqui");
		Assert.assertEquals("unqui", user.password);
	}
	
	@Test
    public void changePasswordInvalidPassword01() throws Exception{
		User user = UserBuilder.aUser().withPassword("1234").build();
		user.getSistem().users.add(user);
		user.getSistem().logSistem.users.put("UserName", "1234");
		try {
			user.changePassword("UserName", "1234", "00");
		}catch (InvalidPasswordException e) {
			System.out.println(e);
		}
	}
	
	@Test
    public void changePasswordInvalidPassword02() throws Exception{
		User user = UserBuilder.aUser().withPassword("1234").build();
		user.getSistem().users.add(user);
		user.getSistem().logSistem.users.put("UserName", "1234");
		try {
			user.changePassword("UserName", "1234", "desarrolloDeAplicaciones");
		}catch (InvalidPasswordException e) {
			System.out.println(e);
		}
	}
	
	@Test
    public void changePasswordUserNameNotmatch() throws Exception{
		User user = UserBuilder.aUser().withPassword("1234").build();
		user.getSistem().users.add(user);
		user.getSistem().logSistem.users.put("UserName", "1234");
		try {
			user.changePassword("UserNames", "1234", "unqui");
		}catch (UserNameNotmatchException e) {
			System.out.println(e);
		}
	}
	
	@Test
    public void changePasswordUserNotExist() throws Exception{
		User user = UserBuilder.aUser().withPassword("1234").build();
		user.getSistem().logSistem.users.put("UserName", "1234");
		try {
			user.changePassword("UserName", "1234", "unqui");
		}catch (UserNotExistException e) {
			System.out.println(e);
		}
	}
	
	@Test
    public void changePasswordPasswordNotmatch() throws Exception{
		User user = UserBuilder.aUser().withPassword("1234").build();
		user.getSistem().users.add(user);
		user.getSistem().logSistem.users.put("UserName", "1234");
		try {
			user.changePassword("UserName", "123421", "unqui");
		}catch (PasswordNotmatchException e) {
			System.out.println(e);
		}
	}
	
	@Test
    public void logIn() throws Exception{
		User user = UserBuilder.aUser().build();
		user.getSistem().users.add(user);
		user.getSistem().logSistem.users.put("UserName", "1234");
		user.logIn("UserName", "1234");
		Assert.assertEquals(true,user.logged);
	}
	
	@Test
    public void logOut() throws Exception{
		User user = UserBuilder.aUser().build();
		user.getSistem().users.add(user);
		user.getSistem().logSistem.users.put("UserName", "1234");
		user.logIn("UserName", "1234");
		Assert.assertEquals(true,user.logged);
		user.logOut("UserName");
		Assert.assertEquals(false,user.logged);
	}
	
	@Test
    public void userNotLogged() throws Exception{
		User user = UserBuilder.aUser().build();
		try{
			user.isLogged();
		}catch (UserNotLoggedException e){
			System.out.println(e);
		}
	}
}