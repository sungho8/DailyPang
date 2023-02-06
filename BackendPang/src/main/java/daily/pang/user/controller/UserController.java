package daily.pang.user.controller;

//import daily.pang.user.model.OauthProvider;
import daily.pang.user.model.User;
import daily.pang.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Transactional
@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/user/signup")
    public Long signUp() {
        System.out.println("user signup");
        User user = User.builder().achievement(0L).build();
        User saveUser = userRepository.save(user);
        return saveUser.getUserId();
    }

    @PostMapping("/user/signin")
    public Long signIn(@RequestParam(value="user_id")Long userId) throws Exception {
        System.out.println("user signin");
        Optional<User> user = userRepository.find(userId);
        if (!user.isPresent()) throw new Exception();
        return user.get().getAchievement();
    }

    @PostMapping("/user/achieve")
    public void achieve(@RequestParam(value="user_id")Long userId) {
        System.out.println("user achieve");
        userRepository.achieveOne(userId);
    }
}
