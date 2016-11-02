package domain.auth0;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import domain.User;
import domain.repositories.GenericRepository;
import domain.repositories.HibernateGenericDao;

@Repository
public class UserTokenRepository extends HibernateGenericDao<UserToken> implements
        GenericRepository<UserToken> {

    @Override
    protected Class<UserToken> getDomainClass() {
        return UserToken.class;
    }

    public UserToken findByUserId(int id) {
        Criteria cr = this.currentSession().createCriteria(this.getDomainClass());
        cr.add(Restrictions.eq("userModel.id", id));
        return (UserToken) cr.uniqueResult();
    }

    public User findByUserToken(String token) {
        Criteria cr = this.currentSession().createCriteria(this.getDomainClass());
        cr.add(Restrictions.eq("token", token));
        UserToken userToken = (UserToken) cr.uniqueResult();
        return userToken.getUserModel();
    }
}
