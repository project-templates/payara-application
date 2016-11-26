package app.application.user;

import app.application.user.command.RegisterUserCommand;
import app.domain.user.User;
import app.domain.user.UserRepository;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class RegisterUserService {
    @Inject
    private Logger logger;
    @Inject
    private UserRepository repository;
    
    public void register(RegisterUserCommand command) {
        User user = new User(command.getLoginId(), command.getUserName());
        this.repository.register(user);
        logger.info("register user ({})", user);
    }
}
