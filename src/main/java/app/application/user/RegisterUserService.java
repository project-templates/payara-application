package app.application.user;

import app.application.user.command.RegisterUserCommand;
import app.domain.user.User;
import app.domain.user.UserRepository;
import app.infrastructure.mail.Mail;
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
        command.getMailAddress().ifPresent(user::setMailAddress);
        
        this.repository.register(user);
        logger.info("register user ({})", user);
        
        command.getMailAddress().ifPresent(mailAddress -> {
            Mail.builder()
                    .to(mailAddress.asString())
                    .subject("ユーザー登録されました")
                    .body("ユーザー登録が完了しました\n" +
                        "ログインID : " + user.getLoginIdAsString() + "\n" +
                        "名前 : " + user.getUserNameAsString() + "\n")
                    .build()
                    .send();
        });
    }
}
