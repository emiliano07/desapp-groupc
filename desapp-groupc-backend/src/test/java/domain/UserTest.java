package domain;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import domain.Event;
import domain.Profile;
import domain.Tour;
import domain.User;
import domain.builders.EventBuilder;
import domain.builders.ProfileBuilder;
import domain.builders.TourBuilder;
import domain.builders.UserBuilder;
import domain.exceptions.NoFriendException;
import domain.types.Type;

public class UserTest {

	@Test
    public void addFriend(){
		User user01 = UserBuilder.aUser().build();
		User user02 = UserBuilder.aUser().build();
		user01.addFriend(user02);
		Assert.assertEquals(user02, user01.getFriends().get(0));
    }
	
	@Test
    public void deleteFriend(){
		User user01 = UserBuilder.aUser().build();
		User user02 = UserBuilder.aUser().build();
		user01.addFriend(user02); 
		user01.deleteFriend(user02);
		Assert.assertEquals(0, user01.getFriends().size());
    }
	
	@Test
    public void searchFriendCorrectly() throws Exception{
		User user01 = UserBuilder.aUser().build();
		User user02 = UserBuilder.aUser().build();
		try {
			user01.addFriend(user02);
			user01.getSistem().users.add(user02);
			User userFounded = user01.searchFriend(user02);
			Assert.assertEquals(user02, userFounded);
		}catch (NoFriendException e) {
			fail(e.toString());
		}
	}
	
	@Test
    public void searchFriendIncorrectly() throws Exception{
		User user01 = UserBuilder.aUser().build();
		User user02 = UserBuilder.aUser().build();
		try {
			user01.searchFriend(user02);
		}catch (NoFriendException e) {
			 System.out.println(e);
		}
	} 
	
	@Test
    public void sendFriendRequest(){
		User user = UserBuilder.aUser().build();
		User friend = UserBuilder.aUser().build();
		user.sendFriendRequest(friend);
		Assert.assertEquals(friend, user.getFriendsRequests().get(0));
	}
	
	@Test
    public void loadProfile() {
		User user = UserBuilder.aUser().build();
		Profile profile = ProfileBuilder.aProfile()
				.withTypeOfMusic(Type.ELECTRONIC)
				.withTypeOfFilm(Type.ADVENTURE)
				.withTypeOfFood(Type.GRILL)
				.withLimitAmount(700)
				.build();
		user.loadProfile(profile);
		Assert.assertEquals(Type.ELECTRONIC, user.getProfile().getTypeOfMusic());
		Assert.assertEquals(Type.ADVENTURE, user.getProfile().getTypeOfFilm());
		Assert.assertEquals(Type.GRILL, user.getProfile().getTypeOfFood());
		Assert.assertEquals(700, user.getProfile().getLimitAmount());
	}
	
	@Test
    public void acceptFriend() {
		User user = UserBuilder.aUser().build();
		User friend = UserBuilder.aUser().build();
		user.acceptFriend(friend);
		Assert.assertEquals(friend, user.getFriends().get(0));
	}
	
	@Test
    public void cancelFriend() {
		User user = UserBuilder.aUser().build();
		User friend = UserBuilder.aUser().build();
		user.sendFriendRequest(friend);
		Assert.assertEquals(1, user.getFriendsRequests().size());
		user.cancelFriend(friend);
		Assert.assertEquals(0, user.getFriendsRequests().size());
	}
	
	@Test
    public void acceptTour() {
		User user = UserBuilder.aUser().build();
		Tour tour = TourBuilder.aTour().build();
		user.acceptTour(tour);
		Assert.assertEquals(tour, user.getTours().get(0));
	}
	
	@Test
    public void addEvent() {
		User user = UserBuilder.aUser().build();
		Event event = EventBuilder.aEvent().build();
		user.addEvent(event);
		Assert.assertEquals(event, user.getEvents().get(0));
	}
}	
