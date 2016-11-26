package app.infrastructure.user;

import app.domain.user.User;
import app.domain.user.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class JpaUserRepository implements UserRepository {
    
    @Inject
    private EntityManager em;
    
    @Override
    public void register(User user) {
        this.em.persist(user);
    }
}
