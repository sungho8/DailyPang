package daily.pang.user.repository;

import daily.pang.user.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserMemoryRepository {

    private Map<String, Object> users = new HashMap<String, Object>();

    public User findByEmail(String email) {
        if (users.containsKey(email)) {
            return (User)users.get(email);
        }
        return null;
    }
    public void register(User user) {
        if (users.containsKey(user.getEmail())) {
            return;
        }
        users.put(user.getEmail(), user);
    }
}
