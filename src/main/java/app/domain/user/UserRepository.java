package app.domain.user;

import java.util.Optional;

public interface UserRepository {
    Optional<User> find(LoginId loginId);
    void register(User user);
}
