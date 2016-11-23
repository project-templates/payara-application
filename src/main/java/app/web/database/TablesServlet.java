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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/database/tables")
public class TablesServlet extends HttpServlet {
    @Inject
    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (JdbcHelper jdbc = new JdbcHelper(this.dataSource)) {
            List<Map<String, Object>> tables = jdbc.selectList(
                    " select s.schemaname" +
                    "       ,t.tablename" +
                    "   from sys.systables t" +
                    "       ,sys.sysschemas s" +
                    "  where t.tabletype='T'" +
                    "    and s.schemaid = t.schemaid" +
                    "  order by s.schemaid asc" +
                    "          ,t.tablename asc",
                    (rs) -> {
                        String schemaName = rs.getString(1);
                        String tableName = rs.getString(2);
                        Map<String, Object> record = new HashMap<String, Object>();
                        record.put("schema", schemaName);
                        record.put("name", tableName);

                        return record;
                    });

            Model model = new Model(req);
            model.put("tables", tables);

            req.getRequestDispatcher("/WEB-INF/jsp/database/tables.jsp").forward(req, resp);
        }
    }
}
