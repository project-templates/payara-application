package app.application.system;

import lombok.extern.slf4j.Slf4j;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Slf4j
@Stateless
public class LogoutService {
    @Inject
    private LoginUser loginUser;
    
    public void logout() {
        log.info("logout successful ({}).", this.loginUser.getLoginId());
        this.loginUser.logout();
    }
}
