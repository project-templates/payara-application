package app.web.database;

import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@Slf4j
@WebServlet("/database/migration")
public class DatabaseMigrationServlet extends HttpServlet {
    @Inject
    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/database/migration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Flyway flyway = new Flyway();
        flyway.setDataSource(this.dataSource);

        if (req.getParameter("migrate") != null) {
            log.info("migrate database");
            flyway.migrate();
        } else if (req.getParameter("clean") != null) {
            log.info("clean database");
            flyway.clean();
        } else if (req.getParameter("cleanMigrate") != null) {
            log.info("clean database");
            flyway.clean();
            log.info("migrate database");
            flyway.migrate();
        }

        req.getRequestDispatcher("/WEB-INF/jsp/database/migration.jsp").forward(req, resp);
    }
}
