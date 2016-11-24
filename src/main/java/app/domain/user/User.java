package app.domain.user;

import app.domain.Id;
import lombok.ToString;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Entity
@ToString
public class User implements Serializable {
    @EmbeddedId
    private Id<User> id;
    @Embedded
    private LoginId loginId;
    @Embedded
    @AttributeOverride(name="value", column=@Column(name="name"))
    private UserName name;
    @Embedded
    private Password password;
    @Embedded
    private MailAddress mailAddress;

    public User(LoginId loginId, UserName name) {
        this.loginId = Objects.requireNonNull(loginId, "ログインIDに null は指定できません");
        this.name = Objects.requireNonNull(name, "ユーザー名に null は指定できません");
        this.password = Password.newPassword();
    }

    public void setMailAddress(MailAddress mailAddress) {
        this.mailAddress = mailAddress;
    }

    public void setName(UserName name) {
        this.name = Objects.requireNonNull(name);
    }

    public void setPassword(Password password) {
        this.password = Objects.requireNonNull(password);
    }

    @Deprecated
    protected User() {}
}
