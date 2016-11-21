package app.web.database;

import app.common.exception.PageNotFoundException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/database/*")
public class DatabaseManagementServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();

        if ("/tables".equals(pathInfo)) {
            req.getRequestDispatcher("/WEB-INF/jsp/database/tables.jsp").forward(req, resp);
            return;
        }

        throw new PageNotFoundException();
    }
}
