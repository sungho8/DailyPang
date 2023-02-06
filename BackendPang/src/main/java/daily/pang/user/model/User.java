package daily.pang.user.model;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
//import org.springframework.security.core.GrantedAuthority;

//import java.util.List;

@Data
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private Long achievement;

    public User(Long userId, Long achievement) {
        this.userId = userId;
        this.achievement = achievement;
    }

    public User() {}
}