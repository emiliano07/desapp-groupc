package domain.services;

import java.util.List;

import javassist.NotFoundException;

import org.springframework.transaction.annotation.Transactional;

import domain.RegisterUser;
import domain.User;
import domain.builders.UserBuilder;
import domain.services.request.LoginUser;

public class RegisterUserService extends GenericService<RegisterUser> {

	private static final long serialVersionUID = -2589951635350079174L;

	@Transactional
	public User login(LoginUser loginUser) throws NotFoundException {
		List<RegisterUser> resgisterUsers = this.retriveAll();
		for (RegisterUser registerUser : resgisterUsers) {
			if (registerUser.is(loginUser))
				return registerUser.getUser();
		}
		throw new NotFoundException("User doesn't exists");
	}

	@Transactional
	public void register(LoginUser loginUser) {
		for (RegisterUser user : this.retriveAll()) {
			if (user.is(loginUser))
				throw new RuntimeException("User already exists");
		}
		this.registerNewUser(loginUser);
	}

	@Transactional
	public User googleConnect(LoginUser loginUser) throws NotFoundException {
		for (RegisterUser registerUser : this.retriveAll()) {
			if (registerUser.is(loginUser))
				return registerUser.getUser();
		}
		this.registerNewUser(loginUser);
		return this.login(loginUser);
	}

	private void registerNewUser(LoginUser loginUser) {
		RegisterUser registerUser = new RegisterUser(loginUser.getEmail(), loginUser.getPassword(),
				new UserBuilder().withName(loginUser.getName()).build());
		this.save(registerUser);
	}
}
