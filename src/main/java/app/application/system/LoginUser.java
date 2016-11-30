package app.application.system;

import app.domain.user.LoginId;
import lombok.ToString;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * ログインユーザーの情報を保持したクラス.
 */
@Named
@ToString
@SessionScoped
public class LoginUser implements Serializable {
    @Inject
    private HttpServletRequest request;
    private LoginId loginId;

    /**
     * 現在のリクエストがログイン済みかどうかを確認する.
     * @return ログイン済みの場合は true
     */
    public boolean isLoggedIn() {
        return this.loginId != null;
    }

    /**
     * ログイン処理を実行する.
     * @param loginId ログインユーザのログインID
     */
    void login(LoginId loginId) {
        this.request.changeSessionId();
        this.loginId = loginId;
    }

    /**
     * ログアウト処理を実行する.
     */
    void logout() {
        this.request.getSession().invalidate();
        this.loginId = null;
    }

    /**
     * 現在ログインしているユーザのログインID を取得する.
     * @return ログインID
     */
    public LoginId getLoginId() {
        return loginId;
    }
}
