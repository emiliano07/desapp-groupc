package domain.builders;

import java.util.ArrayList;

import domain.Event;
import domain.LogSistem;
import domain.Profile;
import domain.Sistem;
import domain.Tour;
import domain.User;
import domain.types.Type;

public class UserBuilder {
	
	private String userName;
	private String password;
	private String mail;
	private Profile profile;
	private ArrayList<Tour> tours;
	private ArrayList<Event> events;
	private ArrayList<User> friends;
	private Sistem sistem;
	private ArrayList<User> friendsRequests;
	private Boolean logged;
	private String nameOfUser;
	private String image;
	
	public UserBuilder(){
		this.userName = "UserName";
		this.password = "Password";
		this.mail = "Mail";
        this.profile = new Profile(Type.NO_FILM, Type.NO_MUSIC, Type.NO_FOOD, 500);
        this.tours = new ArrayList<Tour>();
        this.events = new ArrayList<Event>();
        this.friends = new ArrayList<User>();
        this.sistem = new Sistem(new LogSistem());
        this.friendsRequests = new ArrayList<User>();
        this.logged = false;
        this.image = "no-image";
        this.nameOfUser = "no-name";
    }
    
	public static UserBuilder aUser(){
        return new UserBuilder();
    }
	
	 public User build(){
		 User user =  new User(sistem, userName, password, mail);
		 user.setProfile(this.profile);
		 user.setTours(tours);
		 user.setEvents(events);
		 user.setFriends(friends);
		 user.setFriendsRequests(friendsRequests);
		 user.setLogged(logged);
		 user.setImage(image);
		 user.setName(nameOfUser);
	     return user;
	 }
	 
	 public UserBuilder withUserName(String userName){
		 this.userName = userName;
	     return this;
	 }
	 
	 public UserBuilder withMail(String mail){
		 this.mail = mail;
	     return this;
	 }
	 
	 public UserBuilder withPassword(String password){
		 this.password = password;
	     return this;
	 }
	 
	 public UserBuilder withProfile(Profile profile){
		 this.profile = profile;
	     return this; 
	 }
	 
	 public UserBuilder withTours(ArrayList<Tour> tours){
		 this.tours = tours;
	     return this;
	 }
	 
	 public UserBuilder withEvents(ArrayList<Event> events){
		 this.events = events;
	     return this;
	 }
	 
	 public UserBuilder withFriends(ArrayList<User> friends){
		 this.friends = friends;
	     return this;
	 }
	 
	 public UserBuilder withFriendsRequests(ArrayList<User> friendsRequests){
		 this.friendsRequests = friendsRequests;
	     return this;
	 }
	 
	 public UserBuilder withLogged(Boolean logged){
		 this.logged = logged;
	     return this;
	 }
	 
	 public UserBuilder withName(String nameOfUser){
		 this.nameOfUser = nameOfUser;
	     return this;
	 }
	 
	 public UserBuilder withImage(String image){
		 this.image = image;
	     return this;
	 }
}