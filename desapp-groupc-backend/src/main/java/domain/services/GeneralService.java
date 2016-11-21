package domain.services;

public class GeneralService {

	private EventService eventService;
	private UserService userService;
	private RegisterUserService registerUserService;
	
	public EventService getEventService() {
		return eventService;
	}

	public void setEventService(final EventService eventService) {
		this.eventService = eventService;
	}
	
	public UserService getUserServicee() {
		return userService;
	}

	public void setUserService(final UserService userService) {
		this.userService = userService;
	}
	
	public RegisterUserService getRegisterUserService() {
		return registerUserService;
	}

	public void setRegisterUserService(final RegisterUserService registerUserService) {
		this.registerUserService = registerUserService;
	}
}
