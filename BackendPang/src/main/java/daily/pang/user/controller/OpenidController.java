//package daily.pang.user.controller;
//
//import daily.pang.AppConfig;
//import daily.pang.user.model.OauthProvider;
//import daily.pang.user.repository.UserRepository;
//import daily.pang.user.model.User;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Optional;
//
//@RestController
//public class OpenidController {
//
//    @Autowired UserRepository userRepository;
//
////    @PostMapping("login/kakao")
//
//    @PostMapping("/login/accessToken/kakao")
//    public boolean login(@RequestParam(value="accessToken")String accessToken) {
//        System.out.println("sample login");
//        if (isAccessTokenValid(accessToken)) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public boolean isAccessTokenValid(String accessToken) {
//        HttpHeaders headers = new org.springframework.http.HttpHeaders();
//        headers.add("Authorization", "Bearer " + accessToken);
//        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(headers);
//
//        RestTemplate rt = new RestTemplate();
//        try {
//            ResponseEntity<String> response = rt.exchange(
//                    "https://kapi.kakao.com/v1/user/access_token_info",
//                    HttpMethod.GET,
//                    entity,
//                    String.class
//            );
//            System.out.println("response");
//            System.out.println(response.getBody());
//        } catch (Exception e) {
//            System.out.println(e);
//            return false;
//        }
//        return true;
//    }
//
//    @PostMapping("/login/openid/kakao")
//    public boolean kakaoOpenidLogin(@RequestParam(value="id_token")String idToken) {
//        System.out.println("kakaoOpenidLogin");
//        if (isIdTokenValid(idToken)) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public boolean isIdTokenValid(String idToken) {
//        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
//        body.add("id_token", idToken);
//        HttpHeaders headers = new org.springframework.http.HttpHeaders();
//        headers.add("Content-Type", "application/x-www-form-urlencoded");
//        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);
//
//        RestTemplate rt = new RestTemplate();
//        try {
//            ResponseEntity<String> response = rt.exchange(
//                    "https://kauth.kakao.com/oauth/tokeninfo",
//                    HttpMethod.POST,
//                    entity,
//                    String.class
//            );
//            System.out.println("response");
//            System.out.println(response);
//        } catch (Exception e) {
//            System.out.println(e);
//            return false;
//        }
//        return true;
//    }
//
//    @GetMapping("/login/test")
//    public String test() {
//        System.out.println("test");
//        return "test";
//    }
//
//    @GetMapping("/login/usersavetest")
//    public User userSaveTest() throws Exception {
//        System.out.println("userSaveTest");
//        Optional<User> user1 = userRepository.find("112@1221.121");
//        if (user1.isPresent()) {
//            throw new Exception();
//        }
//        User user = User.builder().oauthProvider(OauthProvider.KAKAO).username("밥").email("112@1221.121").accessToken("accessToken").refreshToken("refreshToken").build();
//        User saveUser = userRepository.save(user);
//        return saveUser;
//    }
//}
