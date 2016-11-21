package app.infrastructure.database;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.sql.DataSource;

@ApplicationScoped
public class DataSourceProvider {

    @Produces
    @Resource(lookup = "java:app/app-ds")
    private DataSource dataSource;
}
