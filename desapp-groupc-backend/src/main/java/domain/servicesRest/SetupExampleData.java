package domain.servicesRest;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import domain.Event;
import domain.LogSistem;
import domain.Profile;
import domain.Sistem;
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
    	Sistem sistem = new Sistem(new LogSistem());
    	Event event = EventBuilder.aEvent()
				.withAddress("Palermo")
				.withName("Rosebar")
				.withDescription("Si estás cansado del trabajo, tuviste un día largo y querés relajarte y divertirte Rosebar es el lugar adecuado. Rosebar es un boliche ubicado en la zona de Palermo en la calle Honduras 5445 que sacar a sus clientes de la rutina diaria adentrándose en un ambiente cómodo y agradable.")
				.withImage("images/rosebar.jpg")
				.build();
    	sistem.addEvent(event);
    	User user = new User(sistem,"Francioni Lucio", "franciolucio","unqui","franciolucio@gmail.com","images/francioniLucio.jpg");
    	userService.save(user);
    	Profile profileUser = ProfileBuilder.aProfile()
    					  .withLimitAmount(500)
    					  .withTypeOfFilm(Type.ACTION)
    					  .withTypeOfFood(Type.PIZZA)
    					  .withTypeOfMusic(Type.ELECTRONIC)
    					  .build();
    	user.setFriends(new ArrayList<User>());
    	this.userService.addProfileForUser(user, profileUser);
    	User friend01 = new User(sistem,"Bart Simpson","bartSimpson","magui","bartSimpson@gmail.com","images/bart.jpg");
    	User friend02 = new User(sistem,"Corre Caminos","correCaminos","atrapame","correCaminos@gmail.com","images/correCaminos.jpg");
    	User friend03 = new User(sistem,"Pato Donald","patoDonald","kuakua","patoDonald@gmail.com","images/patoDonald.jpg");
    	User friend04 = new User(sistem,"Demonio De Tazmania","DemonioDeTazmania","12345","DemonioDeTazmania@gmail.com","images/Demonio_de_tazmania.jpg");
    	Profile profileFriend01 = ProfileBuilder.aProfile()
				  .withLimitAmount(900)
				  .withTypeOfFilm(Type.ADVENTURE)
				  .withTypeOfFood(Type.PASTA)
				  .withTypeOfMusic(Type.CLASSIC)
				  .build();
    	Profile profileFriend02 = ProfileBuilder.aProfile()
				  .withLimitAmount(420)
				  .withTypeOfFilm(Type.COMEDY)
				  .withTypeOfFood(Type.GRILL)
				  .withTypeOfMusic(Type.REGGAETON)
				  .build();
    	Profile profileFriend03 = ProfileBuilder.aProfile()
				  .withLimitAmount(345)
				  .withTypeOfFilm(Type.FANTASY)
				  .withTypeOfFood(Type.FAST_FOOD)
				  .withTypeOfMusic(Type.POP)
				  .build();
    	Profile profileFriend04 = ProfileBuilder.aProfile()
				  .withLimitAmount(100)
				  .withTypeOfFilm(Type.COMEDY)
				  .withTypeOfFood(Type.GRILL)
				  .withTypeOfMusic(Type.ELECTRONIC)
				  .build();
    	this.userService.addProfileForUser(user, profileFriend01);
    	this.userService.addProfileForUser(user, profileFriend02);
    	this.userService.addProfileForUser(user, profileFriend03);
    	this.userService.addProfileForUser(user, profileFriend04);
    	this.userService.addFriendForUser(user, friend01);
    	this.userService.addFriendForUser(user, friend02);
    	this.userService.addFriendForUser(user, friend03);
    	this.userService.addFriendForUser(user, friend04);
    	Event event01 = EventBuilder.aEvent()
    					.withAddress("Palermo")
    					.withName("Rosebar")
    					.withDescription("Si estás cansado del trabajo, tuviste un día largo y querés relajarte y divertirte Rosebar es el lugar adecuado. Rosebar es un boliche ubicado en la zona de Palermo en la calle Honduras 5445 que sacar a sus clientes de la rutina diaria adentrándose en un ambiente cómodo y agradable.")
    					.withImage("images/rosebar.jpg")
    					.build();
    	Event event02 = EventBuilder.aEvent()
    					.withAddress("Palermo Soho")
    					.withName("KIKA")
    					.withDescription("Nueva disco ubicada entre Palermo Soho y Palermo Hollywood. Fácil acceso desde cualquier punto de Capital o Gran Buenos Aires. Una perfecta combinación de espacios y servicios, 800m2 distribuidos en dos plantas con capacidad para 1200 personas.")
    					.withImage("images/kika.jpg")
    					.build();
    	Event event03 = EventBuilder.aEvent()
    					.withAddress("Puerto Madero")
    					.withName("Asia de Cuba")
    					.withDescription("Asia de Cuba es un boliche ubicado en el barrio de Puerto Madero a metros del Hotel Hilton. Cubierto por una temática oriental con lamparas de papel y tonos anaranjados es un lugar especial para vivir una cena con platos extravagantes e internacionales que después se transforma en una de las discos mas prestigiosas de Buenos Aires.")
    					.withImage("images/Asia_de_Cuba.jpg")
    					.build();
    	Event event04 = EventBuilder.aEvent()
				.withAddress("Quilmes")
				.withName("El Bosque")
				.withDescription("Cuatro pistas de baile con la mejor música e iluminación. Cada una de ellas te ofrece un estilo diferente, para que tu noche tenga el entorno que vos prefieras. Además, en una de estas pistas podés disfrutar de una gran variedad de shows.")
				.withImage("images/elBosque.jpg")
				.build();
    	this.eventService.save(event01);
    	this.eventService.save(event02);
    	this.eventService.save(event03);
    	this.eventService.save(event04);
    }
}
