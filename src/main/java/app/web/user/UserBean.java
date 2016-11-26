package app.web.user;

import lombok.Data;

@Data
public abstract class UserBean {
    protected String loginId;
    protected String userName;
}
