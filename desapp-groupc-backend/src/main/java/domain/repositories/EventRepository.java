package domain.repositories;

import java.util.List;

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
	
	public int getCountEvents(int quantity) {
		String hql = "SELECT COUNT(*) FROM " + Event.class.getName();
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		return (int) ((Long) query.uniqueResult() / quantity);
	}
	
	public List<Event> getEvents(Integer pages, Integer quantity) {
		return paginationInTable(Event.class, pages, quantity);
	}
	
	@SuppressWarnings("unchecked")
	private <E> List<E> paginationInTable(Class<E> class1, Integer pages, Integer quantity) {
		String hql = "SELECT r " + " FROM " + class1.getName() + " r ";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setFirstResult(pages * quantity);
		query.setMaxResults(quantity);
		return query.list();
	}
}
