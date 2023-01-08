package daily.pang.user.model;


import lombok.Builder;
import lombok.Data;
//import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data
@Builder
public class User {

    private String registrationId;
    private String oauthProvider;
    private String username;
    private String email;
    private String accessToken;
    private String refreshToken;
    private String oepnidToken;
//    private List<? extends GrantedAuthority> authorities;
}
