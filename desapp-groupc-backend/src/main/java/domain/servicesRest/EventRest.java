package domain.servicesRest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import domain.Event;
import domain.builders.EventBuilder;
import domain.services.EventService;

@Path("/event")
public class EventRest {

	EventService eventService;
	
	public EventRest() {}
	
	public EventRest(EventService eventService) {
		this.eventService = eventService;
	}

	@GET
	@Path("event")
	@Produces("application/json")
	public Event event() {
		Event event = EventBuilder.aEvent()
				.withAddress("Merlo")
				.withLimitOfPersons(4)
				.withAmount(1000)
				.build();
		return event;
	}
	
	@GET
	@Path("/allEvents")
	@Produces("application/json")
	public List<Event> allEvents() {
		return eventService.getEventRepository().findAll();
	}

	@GET
	@Path("/getEvent/{eventId}")
	@Produces("application/json")
	public Event findPostsPublishedByAuthorId(@PathParam("eventId") final int id) {
		return eventService.getEventRepository().findById(id);
	}
	
	@GET
	@Path("/howMuchEvents")
	@Produces("application/json")
	public int howMuchEvents() {
		return eventService.getCountEvents(10);
	}
	
	@GET
	@Path("/events/{page}")
	@Produces("application/json")
	public List<Event> getEvents( @PathParam("page") final Integer page) {
		return eventService.getEvents(page,10);
	}
	
	@GET
	@Path("/eventsSearch/{limitOfPerson}")
	@Produces("application/json")
	public List<Event> getEventsSearch(@PathParam("limitOfPerson") final Integer limitOfPerson) {
		List<Event> events = eventService.getEventRepository().findAll();
		List<Event> filtradoDeEventos = new ArrayList<Event>();
		for(Event e : events){
			if(e.limitOfPersons >= limitOfPerson){
				filtradoDeEventos.add(e);
			}
		}
		return filtradoDeEventos;
		}
}
