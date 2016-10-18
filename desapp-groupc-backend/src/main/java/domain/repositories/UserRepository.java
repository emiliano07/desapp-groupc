package domain.repositories;

import org.hibernate.Query;

import domain.User;

public class UserRepository  extends HibernateGenericDao<User> implements GenericRepository<User> {

	private static final long serialVersionUID = 4077798780803361296L;

	@Override
	protected Class<User> getDomainClass() {
		return User.class;
	}
	
	public User getUserByEmail(String mail){
        String hql = "SELECT u FROM " + User.class.getName() + " u " +
                "WHERE u.mail = :mail";
        Query query =  getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("mail",mail);

        User user = (User) query.uniqueResult();

        return user;
    }

    public User getUserByUserName(String userName){
        String hql = "SELECT u FROM " + User.class.getName() + " u " +
                "WHERE u.userName = :userName";
        Query query =  getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("userName",userName);

        User user = (User) query.uniqueResult();

        return user;
    }
}
