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
public class MailAddress implements Serializable {
    @Column(name="mail_address")
    private String value;

    public MailAddress(String value) {
        this.value = Objects.requireNonNull(value);
    }

    @Deprecated
    protected MailAddress() {}
}
