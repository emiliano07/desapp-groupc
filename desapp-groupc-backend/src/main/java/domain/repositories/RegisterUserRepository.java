package domain.repositories;

import domain.RegisterUser;

public class RegisterUserRepository extends HibernateGenericDao<RegisterUser>
		implements GenericRepository<RegisterUser> {

	private static final long serialVersionUID = -4719734785626291563L;

	@Override
	protected Class<RegisterUser> getDomainClass() {
		return RegisterUser.class;
	}
}
