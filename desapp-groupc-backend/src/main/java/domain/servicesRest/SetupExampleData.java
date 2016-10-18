package domain.servicesRest;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import domain.Event;
import domain.Profile;
import domain.User;
import domain.builders.EventBuilder;
import domain.builders.ProfileBuilder;
import domain.exceptions.SingUpException;
import domain.services.EventService;
import domain.services.UserService;
import domain.types.Type;


public class SetupExampleData {
    
	@Autowired
	EventService eventService;
	UserService userService;
   
    public SetupExampleData() {}

    public SetupExampleData(EventService eventService,UserService userService){
        this.eventService = eventService;
        this.userService = userService;
    }

    public EventService getEventService() {
        return eventService;
    }

    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }
    
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Transactional
    public void init() throws SingUpException {
    	User user = userService.signUp("franciolucio","unqui","franciolucio@gmail.com");
    	Profile profile = ProfileBuilder.aProfile()
    					  .withLimitAmount(500)
    					  .withTypeOfFilm(Type.ACTION)
    					  .withTypeOfFood(Type.PIZZA)
    					  .withTypeOfMusic(Type.ELECTRONIC)
    					  .build();
    	user.setFriends(new ArrayList<User>());
    	this.userService.addProfileForUser(user, profile);
    	User friend01 = new User("emimancu","sanlorenzo","emimancu@gmail.com");
    	User friend02 = new User("alanmarino","rockandroll","alanmarino@gmail.com");
    	User friend03 = new User("martinez","1234","martinez@gmail.com");
    	User friend04 = new User("lopez","12345","lopez@gmail.com");
    	User friend05 = new User("juanPablo","123456","juanPablo@gmail.com");
    	User friend06 = new User("juanMartin","1234567","juanMartin@gmail.com");
    	User friend07= new User("ivanFernandez","12345678","ivanFernandez@gmail.com");
    	User friend08 = new User("joseLuis12","123456789","joseLuis12@gmail.com");
    	
    	
    	this.userService.addFriendForUser(user, friend01);
    	this.userService.addFriendForUser(user, friend02);
    	this.userService.addFriendForUser(user, friend03);
    	this.userService.addFriendForUser(user, friend04);
    	this.userService.addFriendForUser(user, friend05);
    	this.userService.addFriendForUser(user, friend06);
    	this.userService.addFriendForUser(user, friend07);
    	this.userService.addFriendForUser(user, friend08);
    	Event event01 = EventBuilder.aEvent().withAddress("Pilar").build();
    	Event event02 = EventBuilder.aEvent().withAddress("Castelar").build();
    	Event event03 = EventBuilder.aEvent().withAddress("Villa Crespo").build();
    	this.eventService.save(event01);
    	this.eventService.save(event02);
    	this.eventService.save(event03);	
    }
}
