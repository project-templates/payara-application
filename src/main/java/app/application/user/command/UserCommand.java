package app.application.user.command;

import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
public abstract class UserCommand {
    protected String loginId;
    protected String userName;
    protected String mailAddress;
}
