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
import domain.services.EventService;
import domain.services.UserService;
import domain.types.Type;
import domain.types.TypeOfScheduler;
import domain.types.TypeOfTour;

@Path("/user")
public class UserRest {

	UserService userService;
	EventService eventService;
	
	public UserRest() {}
	
	public UserRest(UserService userService,EventService eventService) {
		this.userService = userService;
		this.eventService = eventService;
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
	
	@GET
	@Path("/addFriend/{userId}/{friendId}")
	@Produces("application/json")
	public List<User> allNewFriendsFromTheUser(@PathParam("userId") final int id, @PathParam("friendId") final int idFriend) throws StainException {
		User user = userService.getUserRepository().findById(id);
		User newFriend = userService.getUserRepository().findById(idFriend);
		user.addFriend(newFriend);
		//userService.delete(user);
		//userService.save(user);
		return user.getFriends();
	}
	
	@GET
	@Path("/deleteFriend/{userId}/{friendId}")
	@Produces("application/json")
	public List<User> allNewDeleteFriendsFromTheUser(@PathParam("userId") final int id, @PathParam("friendId") final int idFriend) throws StainException {
		User user = userService.getUserRepository().findById(id);
		User newFriend = userService.getUserRepository().findById(idFriend);
		user.deleteFriend(newFriend);
		userService.updateUser(user);
		userService.updateUser(newFriend);
		return user.getFriends();
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
	
	@GET
	@Path("/updateProfile/{userId}/{film}/{food}/{music}/{amount}")
	@Produces("application/json")
	public User updateProfile(@PathParam("userId") final int id, @PathParam("film") final Type typeOfFilm, @PathParam("food") final Type typeOfFood, @PathParam("music") final Type typeOfMusic, @PathParam("amount") final int limitAmount) throws StainException {
		User user = userService.getUserRepository().findById(id);
		user.profile.typeOfFilm = typeOfFilm;
		user.profile.typeOfMusic = typeOfMusic;
		user.profile.typeOfFood = typeOfFood;
		user.profile.limitAmount = limitAmount;
		userService.updateProfile(user);
		return user;
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
	
	@POST
    @Path("assist/{userId}/{eventId}")
    @Consumes("application/json")
    public Response assistEvent(@PathParam("userId") final int idUser,@PathParam("eventId") final int idEvent){
        User user = userService.getUserRepository().findById(idUser);
		Event event = eventService.getEventRepository().findById(idEvent);
		user.addEventGo(event);
		//userService.updateUser(user);
		return Response.ok().tag("Se actualizo el usuario correctamente").build();
    }
	
	@POST
    @Path("notAssist/{userId}/{eventId}")
    @Consumes("application/json")
    public Response notAssistEvent(@PathParam("userId") final int idUser,@PathParam("eventId") final int idEvent){
        User user = userService.getUserRepository().findById(idUser);
		Event event = eventService.getEventRepository().findById(idEvent);
		user.removeEventGo(event);
		//userService.updateUser(user);
		return Response.ok().tag("Se actualizo el usuario correctamente").build();
    }
	
	@GET
	@Path("/assistEvent/{userId}/{eventId}")
	@Produces("application/json")
	public Boolean assistToAEvent(@PathParam("userId") final int idUser, @PathParam("eventId") final int idEvent) {
		User user = userService.getUserRepository().findById(idUser);
		Event event = eventService.getEventRepository().findById(idEvent);
		return user.assist(event);
	}
	
	@GET
	@Path("/profileOf/{userId}")
	@Produces("application/json")
	public Profile getProfileOfAUser(@PathParam("userId") final int id) {
		return userService.getUserRepository().findById(id).profile;
	}
	
	@GET
	@Path("/optionalFriends/{userId}")
	@Produces("application/json")
	public List<User> getoptionalFriends(@PathParam("userId") final int id) {
		return userService.getUserRepository().findById(id).optionalFriends();
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
