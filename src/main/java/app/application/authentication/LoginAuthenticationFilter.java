package app.application.authentication;

import app.application.system.LoginUser;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@WebFilter("*.xhtml")
public class LoginAuthenticationFilter implements Filter {
    @Inject
    private LoginUser loginUser;
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (needsLoginCheck(request) && !this.loginUser.isLoggedIn()) {
            http(response).sendRedirect(http(request).getContextPath() + "/system/login.xhtml");
        } else {
            chain.doFilter(request, response);
        }
    }
    
    private boolean needsLoginCheck(ServletRequest request) {
        return !this.isLoginPage(request)
                && !this.isCssRequest(request);
    }
    
    private boolean isLoginPage(ServletRequest request) {
        return http(request).getRequestURI().endsWith("/system/login.xhtml");
    }
    
    private boolean isCssRequest(ServletRequest request) {
        return Objects.equals(http(request).getParameter("ln"), "css");
    }
    
    private HttpServletRequest http(ServletRequest request) {
        return (HttpServletRequest)request;
    }
    
    private HttpServletResponse http(ServletResponse response) {
        return (HttpServletResponse)response;
    }

    @Override public void init(FilterConfig filterConfig) throws ServletException {}
    @Override public void destroy() {}
}
