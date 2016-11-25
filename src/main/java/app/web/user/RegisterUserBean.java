package app.web.user;

import lombok.Data;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
@Data
public class RegisterUserBean implements Serializable {
    private String loginId;
    private String userName;

    public void submit() {
        
    }
}
