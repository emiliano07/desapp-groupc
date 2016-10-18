package domain.repositories;

import org.hibernate.Query;

import domain.Event;


public class EventRepository extends HibernateGenericDao<Event> implements GenericRepository<Event> {

	private static final long serialVersionUID = 4077798780803361296L;

	@Override
	protected Class<Event> getDomainClass() {
		return Event.class;
	}

	public Event getEventById(int id) {
		String hql = "SELECT e FROM " + Event.class.getName() + " e "
				+ "WHERE e.id = :id";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		Event event = (Event) query.uniqueResult();
		return event;
	}
	
}
