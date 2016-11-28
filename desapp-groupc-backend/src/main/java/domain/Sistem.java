package domain;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import domain.exceptions.NoFriendException;
import domain.exceptions.UserNotExistException;
import domain.types.TypeOfScheduler;
import domain.types.TypeOfTour;

public class Sistem extends Entity{
	
	private static final long serialVersionUID = -2734681258305935229L;
	public List<User> users;
	public List<Event> allEvents;
	public LogSistem logSistem;
	
	public Sistem(){}
	public Sistem(LogSistem logSistem){
		this.users = new ArrayList<User>();
		this.allEvents = new ArrayList<Event>();
		this.logSistem = logSistem;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Frindship Methods
	//////////////////////////////////////////////////////////////////////////////////////////////////////////

	public User searchFriend(User user) throws Exception{
		this.existsUser(user);
		User usuario = null;
		for (User u  : users){
			if(users.contains(u))
				usuario = u;
		}
		return usuario;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Tour Methods
	//////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void addEvent(Event event){
		this.allEvents.add(event);
	}
	
	public void existsUser(User user) throws Exception{
		if(! this.users.contains(user)){
			throw new NoFriendException();
		}
	}
	
	public Tour newTour(TypeOfTour typeOfTour, DateTime date, TypeOfScheduler scheduler, int limitAmount, int friends){
		Tour tour = new Tour(typeOfTour, date, scheduler, limitAmount, friends);
		this.generateEventOptions(tour);
		return tour;
	}

	public void generateEventOptions(Tour tour){
	//Deberia hacer una query que traiga los eventos para una fecha, horario, limite de personas y monto indicados
		ArrayList<Event> events = new ArrayList<Event>();
		for(Event event: this.allEvents){
			if(this.conditionA(event, tour) && this.conditionB(event, tour) && this.conditionC(event, tour) && this.conditionD(event, tour)){
				events.add(event);
			}
		}
		tour.setEventOptions1(events);
		tour.setEventOptions2(events);
	}  
	
	private Boolean conditionA(Event event, Tour tour){
		return event.getDate().equals(tour.getDate());
	}
	
	private Boolean conditionB(Event event, Tour tour){
		return event.getScheduler().equals(tour.getScheduler());
	}
	
	private Boolean conditionC(Event event, Tour tour){
		return event.getLimitOfPersons() <= tour.getFriends();
	}
	
	private Boolean conditionD(Event event, Tour tour){
		return event.getAmount() <= tour.getLimitAmount();
	}
	 
	public void selectEvent1ForATour(Event event, Tour tour){
		tour.addEvent1(1, event);
		tour.eventOptions2.remove(event);
		this.refreshEvents2(tour);
	}

	public void selectEvent2ForATour(Event event, Tour tour){
		tour.addEvent1(2, event);
	}
	
	private void refreshEvents2(Tour tour) {
		ArrayList<Event> result = new ArrayList<Event>();
		for(Event event: tour.getEventOptions2()){
			if(this.conditionE(event, tour)){
				result.add(event);
			}
		}
		tour.setEventOptions2(result);
	}
	
	private Boolean conditionE(Event event, Tour tour){
		return event.getAmount() <= (tour.getLimitAmount() - tour.getEvent1().getAmount());
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Login Methods
	//////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void registerNewUser(String nameOfUser,String userName, String password, String mail,String image) throws Exception{
		this.logSistem.newUser(userName, password);
		User user = new User(this, nameOfUser,userName, password, mail,image);
		this.users.add(user);
		this.logSistem.users.put(userName, password);
	}
	
	public void changePassword(String userName, String oldPassword, String newPassword)throws Exception{
		this.logSistem.changePassword(userName, oldPassword, newPassword);
		this.obtainUser(userName).setPassword(newPassword);
	}
	
	public User obtainUser(String userName) throws Exception{
		for(User user: this.users){
			if(user.getName() == userName){
				return user;
			}
		}
		throw new UserNotExistException();
	}
	
	public void logIn(String userName, String password) throws Exception{
		this.logSistem.logIn(userName, password);
		this.obtainUser(userName).setLogged(true);
	}

	public void logOut(String userName) throws Exception{
		this.obtainUser(userName).setLogged(false);
	}
}