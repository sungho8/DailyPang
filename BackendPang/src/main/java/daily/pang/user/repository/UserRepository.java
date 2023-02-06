package daily.pang.user.repository;

import daily.pang.user.model.User;

//import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);

    Optional<User> find(Long userId);

    void achieveOne(Long userId);
}
