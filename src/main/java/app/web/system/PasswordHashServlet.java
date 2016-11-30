package app.web.system;

import app.common.util.password.PasswordUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/password")
public class PasswordHashServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String value = req.getParameter("value");
        if (value != null) {
            String hashed = PasswordUtil.hash(value);
            req.setAttribute("hashed", hashed);
        }
        
        req.getRequestDispatcher("/WEB-INF/jsp/system/password.jsp").forward(req, resp);
    }
}
