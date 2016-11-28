package app.application.user.command;

import app.domain.user.LoginId;
import app.domain.user.MailAddress;
import app.domain.user.UserName;
import lombok.ToString;

import java.util.Optional;

@ToString(callSuper = true)
public class RegisterUserCommand extends UserCommand {
    public LoginId getLoginId() {
        return new LoginId(this.loginId);
    }
    
    public UserName getUserName() {
        return new UserName(this.userName);
    }
    
    public Optional<MailAddress> getMailAddress() {
        if (this.mailAddress == null || this.mailAddress.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(new MailAddress(this.mailAddress));
        }
    }
}
