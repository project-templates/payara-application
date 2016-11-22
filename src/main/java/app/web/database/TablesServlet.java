package app.web.database;

import javax.annotation.Resource;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/database/tables")
public class TablesServlet extends HttpServlet {
    @Inject
    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection con = this.dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement("select tablename from sys.systables where TABLETYPE='T'");
             ResultSet rs = ps.executeQuery();) {

            List<String> tableNames = new ArrayList<>();

            while (rs.next()) {
                String tableName = rs.getString(1);
                tableNames.add(tableName);
            }

            Map<String, List<String>> model = new HashMap<>();
            model.put("tableNames", tableNames);

            req.setAttribute("model", model);

            req.getRequestDispatcher("/WEB-INF/jsp/database/tables.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
