package app.web.user;

import app.domain.user.User;
import app.domain.user.UserRepository;
import lombok.Data;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Data
@Named
@RequestScoped
public class UserListBean {
    private List<User> userList;

    @Inject
    private UserRepository userRepository;

    public void init() {
        this.userList = this.userRepository.findAll().toList();
    }
}
