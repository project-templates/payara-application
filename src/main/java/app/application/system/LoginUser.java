package app.application.system;

import app.domain.user.LoginId;
import lombok.ToString;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

@ToString
@SessionScoped
public class LoginUser implements Serializable {
    @Inject
    private HttpServletRequest request;
    private LoginId loginId;
    
    public void login(LoginId loginId) {
        this.request.changeSessionId();
        this.loginId = loginId;
    }
    
    public boolean isLoggedIn() {
        return this.loginId != null;
    }
    
    public void logout() {
        this.request.getSession().invalidate();
        this.loginId = null;
    }
}
