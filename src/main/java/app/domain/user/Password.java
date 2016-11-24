package app.domain.user;

import app.common.util.password.PasswordUtil;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class Password implements Serializable {
    @Column(name="password")
    private String hashedPassword;

    public static Password newPassword() {
        String newPassword = PasswordUtil.newPassword();
        return new Password(newPassword);
    }

    private Password(String plainPassword) {
        this.hashedPassword = PasswordUtil.hash(plainPassword);
    }

    @Deprecated
    protected Password() {}

    @Override
    public String toString() {
        return "Password{******************************}";
    }
}
