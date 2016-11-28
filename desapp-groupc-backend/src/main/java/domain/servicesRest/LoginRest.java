package domain.servicesRest;

import javassist.NotFoundException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import domain.User;
import domain.services.RegisterUserService;
import domain.services.request.LoginUser;

@Path("/login")
public class LoginRest {

	private RegisterUserService registerUserService;

	public RegisterUserService getRegisterUserService() {
		return registerUserService;
	}

	public void setRegisterUserService(RegisterUserService registerUserService) {
		this.registerUserService = registerUserService;
	}

	@POST
	@Path("/connect")
	@Consumes("application/json")
	@Produces("application/json")
	public Response login(LoginUser loginUser) {
		try {
			User user = getRegisterUserService().login(loginUser);
			return Response.ok(user).build();
		} catch (NotFoundException e) {
			return Response.serverError().build();
		}
	}

	@POST
	@Path("/googleconnect")
	@Consumes("application/json")
	@Produces("application/json")
	public Response googleConnect(LoginUser loginUser) {
		try {
			User user = this.getRegisterUserService().googleConnect(loginUser);
			return Response.ok(user).build();
		} catch (NotFoundException e) {
			return Response.serverError().build();
		}
	}

}
