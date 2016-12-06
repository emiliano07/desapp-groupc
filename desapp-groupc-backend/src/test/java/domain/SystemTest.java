package domain;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import domain.builders.EventBuilder;
import domain.builders.TourBuilder;
import domain.builders.UserBuilder;
import domain.types.TypeOfScheduler;
import domain.types.TypeOfTour;

public class SystemTest {
	
	@Test
    public void newTour() {
		User user = UserBuilder.aUser().build();
		Tour tour = user.newTour(TypeOfTour.GASOLERA, new DateTime(), TypeOfScheduler.MORNING, 500, 0);
		Assert.assertEquals(TypeOfTour.GASOLERA, tour.getType());
	}
	
	@Test
    public void selectEvent1ForATour() {
		User user = UserBuilder.aUser().build();
		Event event = EventBuilder.aEvent().build();
		Tour tour = TourBuilder.aTour().build();
		user.getSistem().selectEvent1ForATour(event, tour);
		Assert.assertEquals(event, tour.getEvent1());
	}

	@Test
    public void selectEvent2ForATour() {
		User user = UserBuilder.aUser().build();
		Event event = EventBuilder.aEvent().build();
		Tour tour = TourBuilder.aTour().build();
		user.getSistem().selectEvent2ForATour(event, tour);
		Assert.assertEquals(event, tour.getEvent2());
	}

	@Test
    public void refreshEvents2() {
		ArrayList<User> friends = new ArrayList<User>();
		friends.add(UserBuilder.aUser().build());
		friends.add(UserBuilder.aUser().build());
		User user = UserBuilder.aUser()
				.withFriends(friends)
				.build();
		Event event01 = EventBuilder.aEvent()
				  .withAmount(200)
				  .build();
		Event event02 = EventBuilder.aEvent()
				  .withAmount(150)
				  .build();
		Event event03 = EventBuilder.aEvent()
				  .withAmount(150)
				  .build();
		user.getSistem().addEvent(event01);
		user.getSistem().addEvent(event02);
		user.getSistem().addEvent(event03);
		Tour t = user.newTour(TypeOfTour.GASOLERA, new DateTime(), TypeOfScheduler.NIGHT, 300, 2);
		user.getSistem().generateEventOptions(t);
		user.getSistem().selectEvent1ForATour(event02, t);
		Assert.assertEquals(1, t.eventOptions2.size());
		Assert.assertEquals(event03, t.eventOptions2.get(0));
	}
}