package app.web.database;


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

@WebServlet("/database/tables/*")
public class TableServlet extends HttpServlet {

    @Inject
    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        String tableName = pathInfo.replace("/", "");

        try (Connection con = this.dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement("select ");) {

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
