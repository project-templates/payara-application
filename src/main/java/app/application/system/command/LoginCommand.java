package app.application.system.command;

import app.domain.user.LoginId;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
public class LoginCommand {
    private String loginId;
    private String password;
    
    public LoginId getLoginId() {
        return new LoginId(this.loginId);
    }
    
    public String getPassword() {
        return this.password;
    }
}
