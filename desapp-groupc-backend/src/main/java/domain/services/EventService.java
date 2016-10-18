package domain.services;

import org.springframework.transaction.annotation.Transactional;

import domain.Event;
import domain.repositories.EventRepository;

public class EventService extends GenericService<Event> {

	private static final long serialVersionUID = 4569767598455351744L;

	private EventRepository eventRepository;

	public EventService() {
	}

	public EventService(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}

	public EventRepository getEventRepository() {
		return eventRepository;
	}

	public void setEventRepository(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}

	@Transactional
	public Event obtenerEvent(int id) {
		return getEventRepository().getEventById(id);
	}

}
