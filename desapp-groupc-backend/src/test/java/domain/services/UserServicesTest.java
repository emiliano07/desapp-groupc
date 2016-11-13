package domain.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.Event;
import domain.User;
import domain.builders.EventBuilder;
import domain.builders.UserBuilder;
import domain.exceptions.StainException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/spring-all-contexts.xml")
public class UserServicesTest {

	@Autowired
	public UserService userService;
	
	@Autowired
	public EventService eventService;

//	@Test
//	public void singUp() {
//		try {
//			userService.signUp("Mancuso Emiliano","emimancu","1234","emimancu@gmail.com","image");
//		} catch (SingUpException e) {
//			Assert.fail("Usuario o Email Existente");
//		}
//	}
	
	@Test
	public void addAEvent() throws StainException {
		 User user = UserBuilder.aUser()
				 	 .withUserName("rockandroll")
				 	 .withPassword("unq2016")
				 	 .withMail("rockandroll@hotmail.com")
				 	 .build();
		 userService.save(user);
		 Event event = EventBuilder.aEvent().withAddress("Varela").build();
		 eventService.save(event);
		 userService.addEventForUser(user,event);
		 Assert.assertEquals(1,user.events.size());
	}
}
