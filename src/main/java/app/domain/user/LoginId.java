package app.domain.user;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Embeddable
@EqualsAndHashCode
@ToString
public class LoginId implements Serializable {
    @Column(name="login_id")
    @LoginValidation
    private String value;

    public LoginId(String value) {
        this.value = value;
    }

    public String asString() {
        return this.value;
    }


    @Pattern(regexp="^[a-zA-Z0-9\\-_]+$", message="ログインIDは半角英数ハイフンアンダーバーのみで入力してください")
    @Size.List({
        @Size(min = 1, message = "ログインIDは{min}文字以上で入力してください"),
        @Size(max = 32, message = "ログインIDは{max}文字以下で入力してください")
    })
    @NotNull
    @Constraint(validatedBy = {})
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface LoginValidation {

        String value() default "";

        String message() default "ログインIDが不正です";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }

    @Deprecated
    protected LoginId() {}
}
