package domain.services;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Transactional;

import domain.Event;
import domain.Profile;
import domain.User;
import domain.exceptions.SingUpException;
import domain.exceptions.StainException;
import domain.repositories.UserRepository;
import domain.types.TypeOfScheduler;
import domain.types.TypeOfTour;

public class UserService extends GenericService<User>{

	private static final long serialVersionUID = 7388405495111949457L;
	
	private UserRepository userRepository;

	public UserService() {
	}

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Transactional
    public User signUp(String name,String userName, String email, String password, String image) throws SingUpException {
        validateUser(userName,email);
        User user = new User (name,userName,password,email);
        user.setImage(image);
        userRepository.save(user);
        user = userRepository.findById(user.getId());
        return user;
    }
	
	 private void validateUser(String userName, String email) throws SingUpException {
		 validateNotExistingUserName(userName);
	     validateNotExistingEmail(email);
	 }

	 private void validateNotExistingEmail(String email) throws SingUpException {
		 User user = userRepository.getUserByEmail(email);
	     if (user != null){
	    	 throw new SingUpException();
	    }
	  }

	 private void validateNotExistingUserName(String userName) throws SingUpException {
		 User user = userRepository.getUserByUserName(userName);
	     if(user != null){
	    	 throw new SingUpException();
	     }
	  }
	 
	 @Transactional
	 public void addEventForUser(User user,Event event) throws StainException{
		 user.addEvent(event);
		 userRepository.update(user);
	 }
	 
	 @Transactional
	 public void addFriendForUser(User user,User friend) {
		 user.addFriend(friend);
		 userRepository.update(user);
	 }
	 
	 @Transactional
	 public void addProfileForUser(User user,Profile profile) {
		 user.loadProfile(profile);
		 userRepository.update(user);
	 }
	 
	 @Transactional
	 public void updateUser(User user) throws StainException {
		 userRepository.saveOrUpdate(user);
	 }
	 
	 @Transactional
	 public List<Event> getEvents(User user,TypeOfTour typeOfTour,DateTime date, TypeOfScheduler scheduler,int limitAmount, List<User>friendsSelect){
		 user.newTour(typeOfTour, date, scheduler, limitAmount, friendsSelect);
	 }
}

