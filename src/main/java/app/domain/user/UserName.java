package app.domain.user;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@ToString
@EqualsAndHashCode
public class UserName implements Serializable {
    @Column(name="user_name")
    private String value;

    public UserName(String value) {
        this.value = Objects.requireNonNull(value);

        if (this.value.isEmpty()) {
            throw new IllegalArgumentException("ユーザー名に空文字は指定できません");
        }
    }

    @Deprecated
    protected UserName() {}
}
