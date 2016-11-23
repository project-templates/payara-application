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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet("/database/tables/*")
public class TableServlet extends HttpServlet {

    @Inject
    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        String tableName = pathInfo.replace("/", "");

        try (JdbcHelper jdbc = new JdbcHelper(this.dataSource)) {
            List<String> columnNames = jdbc.selectStringList(
                                            "select c.columnname" +
                                            "  from sys.systables t" +
                                            "      ,sys.syscolumns c" +
                                            " where t.tablename = ?" +
                                            "   and c.referenceid = t.tableid" +
                                            " order by c.columnnumber asc", tableName);

            List<Map<String, Object>> records = jdbc.selectMapList("select * from " + tableName);

            Model model = new Model(req);
            model.put("columnNames", columnNames);
            model.put("records", records);
            model.put("tableName", tableName);
        }

        req.getRequestDispatcher("/WEB-INF/jsp/database/table.jsp").forward(req, resp);
    }
}
