package app.domain.user;

import app.common.util.password.PasswordUtil;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * ハッシュ化されたパスワードを表します.
 * <p>
 * このクラスはパスワードをハッシュ化された値で保持し、平文の状態では保持しません.
 */
@Embeddable
@EqualsAndHashCode
public class Password implements Serializable {
    @Column(name="password")
    private String hashedPassword;

    /**
     * 新しいパスワードを発行します.
     * @return 新しいパスワード
     */
    public static Password newPassword() {
        String newPassword = PasswordUtil.newPassword();
        return new Password(newPassword);
    }

    private Password(String plainPassword) {
        this.hashedPassword = PasswordUtil.hash(plainPassword);
    }

    /**
     * 指定した平文のパスワードが、このパスワードと等しいか検証します.
     * @param plainPassword 平文のパスワード
     * @return 等しい場合は true
     */
    public boolean isEquals(String plainPassword) {
        return PasswordUtil.isEquals(plainPassword, this.hashedPassword);
    }

    /**
     * このインスタンスが持つパスワードのハッシュ値は、セキュリティ的な観点から文字列表現の中には含まれません.
     * <p>
     * 疑似的に伏字を使った文字列表現が返されますが、その桁数は実際の値とは何の関係もありません.
     * 
     * @return このインスタンスの文字列表現
     */
    @Override
    public String toString() {
        return "Password{******************************}";
    }

    @Deprecated
    protected Password() {}
}
