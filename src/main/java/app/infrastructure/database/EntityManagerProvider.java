package app.infrastructure.database;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class EntityManagerProvider {
    
    @Produces
    @PersistenceContext(unitName = "app-unit")
    private EntityManager em;
}
