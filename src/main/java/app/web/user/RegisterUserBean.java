package app.web.user;

import app.application.user.RegisterUserService;
import app.application.user.command.RegisterUserCommand;
import app.web.common.JsfUtil;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class RegisterUserBean extends UserBean implements Serializable {
    
    @Inject
    private RegisterUserService service;
    
    public String submit() {
        
        RegisterUserCommand command = new RegisterUserCommand();
        command.setLoginId(this.loginId);
        command.setUserName(this.userName);

        this.service.register(command);

        JsfUtil.addMessage("登録が完了しました");
        
        return "/user/register-user.xhtml?faces-redirect=true";
    }
}
