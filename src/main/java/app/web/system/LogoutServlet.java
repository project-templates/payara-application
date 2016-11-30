package app.web.system;

import app.application.system.LogoutService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/system/logout")
public class LogoutServlet extends HttpServlet {
    @Inject
    private LogoutService service;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.service.logout();
        resp.sendRedirect(req.getContextPath() + "/system/login.xhtml");
    }
}
