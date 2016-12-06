package domain.servicesRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import domain.Event;
import domain.LogSistem;
import domain.Profile;
import domain.Sistem;
import domain.User;
import domain.builders.EventBuilder;
import domain.builders.ProfileBuilder;
import domain.services.EventService;
import domain.services.RegisterUserService;
import domain.services.UserService;
import domain.types.Type;
import domain.types.TypeOfScheduler;


public class SetupExampleData {
    
	@Autowired
	EventService eventService;
	UserService userService;
	RegisterUserService registerUserService;
   
    public SetupExampleData() {}

    public SetupExampleData(EventService eventService,UserService userService,RegisterUserService registerUserService){
        this.eventService = eventService;
        this.userService = userService;
        this.registerUserService = registerUserService;
    }
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Getters & Setters
    //////////////////////////////////////////////////////////////////////////////////////////////////////////

    public EventService getEventService() {
        return eventService;
    }
    
    public UserService getUserService() {
        return userService;
    }
    
    public RegisterUserService getRegisterUserService() {
		return registerUserService;
	}
    
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }
    
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
    public void  setRegisterUserService(RegisterUserService registerUserService) {
    	this.registerUserService = registerUserService;
	}
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Set Up
    //////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Transactional
    public void init() throws Exception {
    	
    	//Sistems
    	LogSistem logSistem = new LogSistem();
    	Sistem sistem = new Sistem(logSistem);
    	
    	//Users (Quedan registrados en el sistem y en el logSistem)
    	User user = new User(sistem, "Francioni Lucio", "franciolucio", "1234","franciolucio@gmail.com", "images/francioniLucio.jpg");
    	//sistem.users.add(user);
    	User friend01 = new User(sistem, "Bart Simpson","bartSimpson","magui","bartSimpson@gmail.com","images/bart.jpg");
    	//sistem.users.add(friend01);
    	User friend02 = new User(sistem, "Corre Caminos","correCaminos","atrapame","correCaminos@gmail.com","images/correCaminos.jpg");
    	//sistem.users.add(friend02);
    	User friend03 = new User(sistem, "Pato Donald","patoDonald","kuakua","patoDonald@gmail.com","images/patoDonald.jpg");
    	//sistem.users.add(friend03);
    	User friend04 = new User(sistem, "Demonio De Tazmania","DemonioDeTazmania","12345","DemonioDeTazmania@gmail.com","images/Demonio_de_tazmania.jpg");
    	//sistem.users.add(friend04);
    	User friend05 = new User(sistem, "Bugs Bunny","bugsbunny","1234567","bugsbunny@gmail.com","images/bugsbunny.jpg");
    	//sistem.users.add(friend05);
    	User friend06 = new User(sistem, "Pato Donald","patoDonald","kuakua","patoDonald@gmail.com","images/patoDonald.jpg");
    	//sistem.users.add(friend05);
    	//Agrego las amistades
    	friend01.addFriend(friend05);
    	friend02.addFriend(friend05);
    	friend03.addFriend(friend05);
    	friend04.addFriend(friend05);
    	friend05.addFriend(friend06);
    	user.addFriend(friend01);
    	user.addFriend(friend02);
    	user.addFriend(friend03);
    	user.addFriend(friend04);
    	
    	//Events
    	Event event01 = EventBuilder.aEvent()
				.withAddress("Palermo")
				.withName("Rosebar")
				.withScheduler(TypeOfScheduler.NIGHT)
				.withLimitOfPersons(10)
				.withAmount(150)
				.withDescription("Si estás cansado del trabajo, tuviste un día largo y querés relajarte y divertirte Rosebar es el lugar adecuado. Rosebar es un boliche ubicado en la zona de Palermo en la calle Honduras 5445 que sacar a sus clientes de la rutina diaria adentrándose en un ambiente cómodo y agradable.")
				.withImage("images/rosebar.jpg")
				.build();
    	Event event02 = EventBuilder.aEvent()
				.withAddress("Palermo Soho")
				.withName("KIKA")
				.withScheduler(TypeOfScheduler.AFTERNOON)
				.withLimitOfPersons(1)
				.withAmount(300)
				.withDescription("Nueva disco ubicada entre Palermo Soho y Palermo Hollywood. Fácil acceso desde cualquier punto de Capital o Gran Buenos Aires. Una perfecta combinación de espacios y servicios, 800m2 distribuidos en dos plantas con capacidad para 1200 personas.")
				.withImage("images/kika.jpg")
				.build();
    	Event event03 = EventBuilder.aEvent()
				.withAddress("Puerto Madero")
				.withName("Asia de Cuba")
				.withScheduler(TypeOfScheduler.NIGHT)
				.withLimitOfPersons(3)
				.withAmount(150)
				.withDescription("Asia de Cuba es un boliche ubicado en el barrio de Puerto Madero a metros del Hotel Hilton. Cubierto por una temática oriental con lamparas de papel y tonos anaranjados es un lugar especial para vivir una cena con platos extravagantes e internacionales que después se transforma en una de las discos mas prestigiosas de Buenos Aires.")
				.withImage("images/Asia_de_Cuba.jpg")
				.build();
    	Event event04 = EventBuilder.aEvent()
    			.withAddress("Quilmes")
    			.withName("El Bosque")
    			.withScheduler(TypeOfScheduler.NIGHT)
				.withLimitOfPersons(3)
				.withAmount(200)
				.withDescription("Cuatro pistas de baile con la mejor música e iluminación. Cada una de ellas te ofrece un estilo diferente, para que tu noche tenga el entorno que vos prefieras. Además, en una de estas pistas podés disfrutar de una gran variedad de shows.")
				.withImage("images/elBosque.jpg")
				.build();
    	//(Al registrar un evento cada usuario tambien lo guarda en el sistema)
    	friend01.addEvent(event01);
    	user.addEvent(event02);
    	friend03.addEvent(event03);
    	user.addEvent(event04);
    	//(Cargo las sugerencias)
    	event01.suggestions.add(event02);
    	event01.suggestions.add(event03);
    	event01.suggestions.add(event04);
    	
    	//Profiles
    	Profile profileUser = ProfileBuilder.aProfile()
				  .withLimitAmount(500)
				  .withTypeOfFilm(Type.ACTION)
				  .withTypeOfFood(Type.PIZZA)
				  .withTypeOfMusic(Type.ELECTRONIC)
				  .build();
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
    	Profile profileFriend05 = ProfileBuilder.aProfile()
				  .withLimitAmount(100)
				  .withTypeOfFilm(Type.COMEDY)
				  .withTypeOfFood(Type.GRILL)
				  .withTypeOfMusic(Type.ELECTRONIC)
				  .build();
    	//(Cargo los perfiles a cada usuario)
    	user.loadProfile(profileUser);
    	friend01.loadProfile(profileFriend01);
    	friend02.loadProfile(profileFriend02);
    	friend03.loadProfile(profileFriend03);
    	friend04.loadProfile(profileFriend04);
    	friend05.loadProfile(profileFriend05);
    	
    	//Guardo el usuario en la base de datos
    	userService.save(user);
    }
}