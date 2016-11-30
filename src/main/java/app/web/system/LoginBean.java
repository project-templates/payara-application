package app.web.system;

import app.application.system.LoginService;
import app.application.system.command.LoginCommand;
import lombok.Data;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Data
@Model
public class LoginBean {
    private String loginId;
    private String password;
    
    @Inject
    private LoginService service;
    
    public String submit() {
        LoginCommand command = new LoginCommand();
        command.setLoginId(this.loginId);
        command.setPassword(this.password);
        
        this.service.login(command);
        
        return "/index.xhtml";
    }
}
