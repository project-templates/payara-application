package app.domain.user;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Objects;

@Embeddable
@ToString
@EqualsAndHashCode
public class MailAddress implements Serializable {
    @Column(name="mail_address")
    @MailAddressValidation
    private String value;

    public MailAddress(String value) {
        this.value = Objects.requireNonNull(value);
    }

    @Deprecated
    protected MailAddress() {}

    public String asString() {
        return this.value;
    }
    
    // https://www.w3.org/TR/html5/forms.html#valid-e-mail-address
    @Pattern(
        regexp="^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$",
        message="メールアドレスの書式が不正です"
    )
    @Size(max = 256, message = "メールアドレスは{max}文字以下で入力してください")
    @Constraint(validatedBy = {})
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface MailAddressValidation {

        String value() default "";

        String message() default "メールアドレスが不正です";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }
}
