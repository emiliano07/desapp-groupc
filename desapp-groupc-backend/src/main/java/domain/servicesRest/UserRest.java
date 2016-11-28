package domain.servicesRest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.eclipse.jetty.http.HttpStatus;
import org.joda.time.DateTime;

import domain.Event;
import domain.Profile;
import domain.Sistem;
import domain.User;
import domain.builders.UserBuilder;
import domain.exceptions.StainException;
import domain.services.UserService;
import domain.types.TypeOfScheduler;
import domain.types.TypeOfTour;

@Path("/user")
public class UserRest {

	UserService userService;
	
	public UserRest() {}
	
	public UserRest(UserService userService) {
		this.userService = userService;
	}

	@POST
	@Path("/newUser")
	@Produces("application/json")
	public User user() {
		User user = UserBuilder.aUser().build();
		return user;
	}
	
	@GET
	@Path("/allUsers")
	@Produces("application/json")
	public List<User> allUsers() {
		return userService.getUserRepository().findAll();
	}
	
	@GET
	@Path("/userFrom/{userId}")
	@Produces("application/json")
	public User getAUser(@PathParam("userId") final int id) {
		return userService.getUserRepository().findById(id);
	}
	
	@GET
	@Path("/allFriends/{userId}")
	@Produces("application/json")
	public List<User> allTheFriendsFromTheUser(@PathParam("userId") final int id) {
		return userService.getUserRepository().findById(id).getFriends();
	}
	
	@POST
	@Path("/addEvent/{userId}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response addEvent(@PathParam("userId") final int id,Event event) {
		Response response;
		try {
		User user = userService.getUserRepository().findById(id);
		userService.addEventForUser(user, event);
        response = Response.ok().tag("El evento fue agregado correctamente").status(HttpStatus.OK_200).build();
		 } catch (StainException e) {
	            response = Response.serverError().tag("No se pudo agregar el Evento").status(HttpStatus.NOT_FOUND_404).build();
	        }
		return response;
	}

	@POST
    @Path("updateUser")
    @Consumes("application/json")
    public Response updateUser(User user){
        try {
            userService.updateUser(user);
            return Response.ok().tag("Se actualizo el usuario correctamente").build();
        } catch (StainException e) {
            return Response.serverError().tag("Error al actualizar el usuario").build();
        }
    }
	
	@GET
	@Path("/profileOf/{userId}")
	@Produces("application/json")
	public Profile getProfileOfAUser(@PathParam("userId") final int id) {
		return userService.getUserRepository().findById(id).profile;
	}
	//@PathParam("date") final DateTime date,/{date}
	@GET
	@Path("/eventsForTour/{userId}/{type}/{scheduler}/{limitAmount}/{friendsSelect}")
	@Produces("application/json")
	public List<Event> getProfileOfAUser(@PathParam("userId") final int id,@PathParam("type") final TypeOfTour type,@PathParam("scheduler") final TypeOfScheduler scheduler,@PathParam("limitAmount") final int limitAmount,@PathParam("friendsSelect") final int friendsSelect) {
		User user = userService.getUserRepository().findById(id);
		return userService.getEvents(user,type,new DateTime(),scheduler,limitAmount,friendsSelect);
	}
	
	@GET
	@Path("/userFromSystem/{userId}")
	@Produces("application/json")
	public Sistem getASistem(@PathParam("userId") final int id) {
		return userService.getUserRepository().findById(id).getSistem();
	}
}
