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
import java.util.Objects;

@Embeddable
@ToString
@EqualsAndHashCode
public class UserName implements Serializable {
    @Column(name="user_name")
    @UserNameValidation
    private String value;

    public UserName(String value) {
        this.value = Objects.requireNonNull(value);

        if (this.value.isEmpty()) {
            throw new IllegalArgumentException("ユーザー名に空文字は指定できません");
        }
    }

    public String asString() {
        return this.value;
    }

    @Size.List({
        @Size(min=1, message = "ユーザー名は{min}文字以上で入力してください"),
        @Size(max = 32, message = "ユーザー名は{max}文字以下で入力してください")
    })
    @NotNull
    @Constraint(validatedBy = {})
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface UserNameValidation {

        String value() default "";

        String message() default "ユーザー名が不正です";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }

    @Deprecated
    protected UserName() {}
}
