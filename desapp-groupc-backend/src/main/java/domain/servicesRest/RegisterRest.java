package domain.servicesRest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import domain.RegisterUser;
import domain.services.RegisterUserService;
import domain.services.request.LoginUser;

@Path("/register")
public class RegisterRest {

	private RegisterUserService registerUserService;

	public RegisterUserService getRegisterUserService() {
		return registerUserService;
	}

	public void setRegisterUserService(RegisterUserService registerUserService) {
		this.registerUserService = registerUserService;
	}

	@GET
	@Path("/allRegisterUser")
	@Produces("application/json")
	public List<RegisterUser> allUsers() {
		return registerUserService.retriveAll();
	}
	
	@POST
	@Path("/newuser")
	@Consumes("application/json")
	@Produces("application/json")
	public Response register(LoginUser loginUser) {
		try {
			getRegisterUserService().register(loginUser);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.serverError().build();
		}

	}

}