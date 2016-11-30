package app.infrastructure.user;

import app.domain.user.LoginId;
import app.domain.user.User;
import app.domain.user.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.Optional;

@ApplicationScoped
public class JpaUserRepository implements UserRepository {
    
    @Inject
    private EntityManager em;

    @Override
    public Optional<User> find(LoginId loginId) {
        TypedQuery<User> query = this.em.createNamedQuery("User.findByLoginId", User.class);
        query.setParameter("loginId", loginId.asString());
        
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public void register(User user) {
        this.em.persist(user);
    }
}
