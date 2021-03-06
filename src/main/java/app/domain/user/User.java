package app.domain.user;

import app.domain.Id;
import lombok.ToString;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.Objects;

/**
 * ユーザー.
 */
@Entity
@Table(name="users")
@ToString
public class User implements Serializable {
    @EmbeddedId
    private Id<User> id;
    @Embedded @Valid
    private LoginId loginId;
    @Embedded @Valid
    @AttributeOverride(name="value", column=@Column(name="name"))
    private UserName name;
    @Embedded @Valid
    private Password password;
    @Embedded @Valid
    private MailAddress mailAddress;

    public User(LoginId loginId, UserName name) {
        this.loginId = Objects.requireNonNull(loginId, "ログインIDに null は指定できません");
        this.name = Objects.requireNonNull(name, "ユーザー名に null は指定できません");
        this.password = Password.newPassword();
    }

    /**
     * このユーザが持つパスワードと指定した平文のパスワードが等しいか検証します.
     * @param plainPassword 平文のパスワード
     * @return パスワードが等しい場合は true
     */
    public boolean isValidPassword(String plainPassword) {
        return this.password.isEquals(plainPassword);
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

    @PrePersist
    private void setupId() {
        this.id = new Id<User>(-1L);
    }
    
    @Deprecated
    protected User() {}

    public String getLoginIdAsString() {
        return this.loginId.asString();
    }

    public String getUserNameAsString() {
        return this.name.asString();
    }
}
