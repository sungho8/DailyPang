package daily.pang;

import daily.pang.user.repository.JpaUserRepository;
import daily.pang.user.repository.UserRepository;
import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    private EntityManager em;

    @Autowired
    public AppConfig(EntityManager em) {
        this.em = em;
    }
    @Bean
    public UserRepository userRepository() {
        return new JpaUserRepository(em);
    }
}
