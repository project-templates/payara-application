package app.web.user;

import app.application.user.RegisterUserService;
import app.application.user.command.RegisterUserCommand;
import app.domain.user.LoginId;
import app.domain.user.MailAddress;
import app.domain.user.UserName;
import app.web.common.JsfUtil;
import lombok.Data;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Data
@Named
@ViewScoped
public class RegisterUserBean implements Serializable {
    @LoginId.LoginValidation
    private String loginId;
    @UserName.UserNameValidation
    private String userName;
    @MailAddress.MailAddressValidation
    private String mailAddress;
    
    @Inject
    private RegisterUserService service;
    
    public String submit() {
        
        RegisterUserCommand command = new RegisterUserCommand();
        command.setLoginId(this.loginId);
        command.setUserName(this.userName);
        command.setMailAddress(this.mailAddress);

        this.service.register(command);

        JsfUtil.addMessage("登録が完了しました");
        
        return "/user/register-user.xhtml?faces-redirect=true";
    }
}
