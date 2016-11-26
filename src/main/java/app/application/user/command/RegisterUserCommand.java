package app.application.user.command;

import app.domain.user.LoginId;
import app.domain.user.UserName;
import lombok.ToString;

@ToString(callSuper = true)
public class RegisterUserCommand extends UserCommand {
    public LoginId getLoginId() {
        return new LoginId(this.loginId);
    }
    
    public UserName getUserName() {
        return new UserName(this.userName);
    }
}
