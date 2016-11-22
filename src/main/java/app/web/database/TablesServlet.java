package app.web.database;

import app.common.util.database.JdbcHelper;
import app.web.common.Model;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet("/database/tables")
public class TablesServlet extends HttpServlet {
    @Inject
    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (JdbcHelper jdbc = new JdbcHelper(this.dataSource)) {
            List<String> tableNames = jdbc.selectStringList("select tablename from sys.systables where tabletype='T'");

            Model model = new Model(req);
            model.put("tableNames", tableNames);

            req.getRequestDispatcher("/WEB-INF/jsp/database/tables.jsp").forward(req, resp);
        }
    }
}
