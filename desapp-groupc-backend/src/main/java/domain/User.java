package domain;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import domain.auth0.GoogleOauthCredential;
import domain.exceptions.UserNotLoggedException;
import domain.types.TypeOfScheduler;
import domain.types.TypeOfTour;

public class User extends Entity{
	
	private static final long serialVersionUID = -8201505803898337489L;
	public String userName;
	public String password;
	public String mail;
	public Profile profile;
	public List<Tour> tours;
	public List<Event> events; //Eventos que agrego
	public List<User> friends;
	public Sistem sistem;  
	public List<User> friendsRequests;
	public Boolean logged;
	public String nameOfUser;
	public String image;
	public GoogleOauthCredential token;
	public List<Integer> eventsGo; //Eventos que voy a ir
	
	public User() {}
	public User(Sistem sistem, String nameOfUser, String userName, String password, String mail,String image){
		this.userName = userName;
		this.password = password;
		this.mail = mail;
		this.profile = null;
		this.tours = new ArrayList<Tour>();
		this.events = new ArrayList<Event>();
		this.friends = new ArrayList<User>();
		this.sistem = sistem;
		this.friendsRequests = new ArrayList<User>();
		this.logged = false;
		this.image = image;
		this.nameOfUser = nameOfUser;
		this.eventsGo = new ArrayList<Integer>();
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Methods
	//////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void loadProfile(Profile profile){
		this.profile = profile;
	}	

	public void addTour(Tour tour){
		this.tours.add(tour);
	}
	
	public void removeEventGo(Integer id){
		this.eventsGo.remove(id);
	}
	
	public void addEventGo(Integer id){
		this.eventsGo.add(id);
	}
	
	public boolean assist(Integer key){
		return this.eventsGo.contains(key);
	}
	
	public void addEvent(Event event){
		this.sistem.addEvent(event);
		this.events.add(event);
	}
	
	public void removeEvent(Event event){
		this.sistem.removeEvent(event);
		this.events.remove(event);
	}

	public Tour newTour(TypeOfTour typeOfTour, DateTime date, TypeOfScheduler scheduler, int limitAmount, int friends){
		Tour tour = new Tour(typeOfTour, date, scheduler, limitAmount, friends);
		return this.sistem.generateEventOptions(tour);
	}

	public void acceptTour(Tour tour){
		this.tours.add(tour);
		tour.makeSuggestions();
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Friendship Methods
	//////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void addFriend(User friend){
		this.friends.add(friend);
	}
	
	
	public User searchFriend(User user) throws Exception{
		return this.sistem.searchFriend(user);
	}
	
	public void sendFriendRequest(User friend){
		this.friendsRequests.add(friend);
	}
	
	public void deleteFriend(User friend){
		this.friends.remove(friend);
		friend.getFriends().remove(this);
	}
	
	public void acceptFriend(User friend){
		this.addFriend(friend);
		friend.addFriend(this);
	}
	
	public void cancelFriend(User friend){
		this.friendsRequests.remove(friend);
	}
	
	public List<User> optionalFriends(){
		List<User> of = new ArrayList<User>();
		int numAleatorio=(int)Math.floor(Math.random()*(friends.size()));
		of = friends.get(numAleatorio).friends;
		for (User f  : this.friends){
			of.remove(f);
		}
		return of;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Login Methods
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void logIn(String userName, String password) throws Exception{
		this.sistem.logIn(userName, password);
	}

	public void logOut(String userName) throws Exception{
		this.sistem.logOut(userName);
	}
	
	public void isLogged() throws Exception{
		if(!this.logged){
			throw new UserNotLoggedException();
		}
	}
	
	public void changePassword(String userName, String oldPassword, String newPassword)throws Exception{
		this.sistem.changePassword(userName, oldPassword, newPassword);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Getters & Setters
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public Profile getProfile() {
		return profile;
	}

	public String getName(){
		return this.userName;
	}
	
	public List<Tour> getTours() {
		return tours;
	}
	
	public List<Event> getEvents() {
		return events;
	}

	public Sistem getSistem() {
		return sistem;
	}
	
	public List<User> getFriends(){
		return this.friends;
	}

	public List<User> getFriendsRequests() {
		return friendsRequests;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
	public void setTours(List<Tour> tours) {
		this.tours = tours;
	}
	
	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public void setFriendsRequests(List<User> friendsRequests) {
		this.friendsRequests = friendsRequests;
	}

	public void setFriends(List<User> friends) {
		this.friends = friends;
	}
	
	public void setLogged(Boolean logged){
		this.logged = logged;
	}
	
	public void setName(String nameOfUser){
		this.nameOfUser = nameOfUser;
	}
	
	public void setImage(String image){
		this.image = image;
	}
	
    public GoogleOauthCredential getToken() {
        return token;
    }

    public void setToken(GoogleOauthCredential token) {
        this.token = token;
    }
}