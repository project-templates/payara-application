package app.domain.user;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@ToString
public class LoginId implements Serializable {
    @Column(name="login_id")
    private String value;

    public LoginId(String value) {
        this.value = value;
    }

    @Deprecated
    protected LoginId() {}
}
