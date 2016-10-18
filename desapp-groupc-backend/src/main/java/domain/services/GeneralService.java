package domain.services;

public class GeneralService {

	private EventService eventService;
	
	public EventService getEventService() {
		return eventService;
	}

	public void setEventService(final EventService eventService) {
		this.eventService = eventService;
	}
}
