package app.infrastructure.database;

import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@Slf4j
@WebListener
public class DatabaseMigration implements ServletContextListener {

    @Resource(lookup="java:app/app-ds")
    private DataSource dataSource;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("Database migration is started.");

        Flyway flyway = new Flyway();
        flyway.setDataSource(this.dataSource);
        flyway.migrate();

        log.info("Database migration is finished.");
    }

    @Override public void contextDestroyed(ServletContextEvent sce) {}
}
