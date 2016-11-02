package domain.auth0;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import domain.repositories.GenericRepository;
import domain.repositories.HibernateGenericDao;

public class GoogleOauthCredentialRepository extends HibernateGenericDao<GoogleOauthCredential> implements
        GenericRepository<GoogleOauthCredential> {

    private static final long serialVersionUID = -4036535812105672112L;

    @Override
    protected Class<GoogleOauthCredential> getDomainClass() {
        return GoogleOauthCredential.class;
    }

    public GoogleOauthCredential findByUserId(String id){
        Criteria cr = this.currentSession().createCriteria(this.getDomainClass());

        cr.add(Restrictions.eq("googleUserId",id));
        return (GoogleOauthCredential) cr.uniqueResult();
    }

}
