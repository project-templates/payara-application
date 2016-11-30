package app.application.system;

import app.application.system.command.LoginCommand;
import app.common.exception.InvalidException;
import app.domain.user.LoginId;
import app.domain.user.User;
import app.domain.user.UserRepository;
import lombok.extern.slf4j.Slf4j;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Slf4j
@Stateless
public class LoginService {
    @Inject
    private LoginUser loginUser;
    @Inject
    private UserRepository userRepository;
    
    public void login(LoginCommand command) {
        LoginId loginId = command.getLoginId();
        
        User user = this.userRepository
                        .find(loginId)
                        .orElseThrow(() -> new InvalidException("ログインIDかパスワードが異なります"));
        
        if (user.isValidPassword(command.getPassword())) {
            this.loginUser.login(loginId);
            log.info("login successful ({}).", loginId);
        } else {
            log.warn("login failure ({}).", loginId);
            throw new InvalidException("ログインIDかパスワードが異なります");
        }
    }
}
