package daily.pang.user.repository;

import daily.pang.user.model.User;
import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class JpaUserRepository implements UserRepository{

    private final EntityManager em;

    public JpaUserRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public User save(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public Optional<User> find(Long userId) {
        List<User> results = em.createQuery("select u from User u WHERE userId=:userId", User.class).setParameter("userId", userId).getResultList();
        return results.stream().findAny();
    }

    @Override
    public void achieveOne(Long userId) {
        em.createQuery("update User set achievement=achievement+1 WHERE userId=:userId").setParameter("userId", userId).executeUpdate();
    }

//    @Override
//    public Optional<User> update(User user) {
//        return Optional.empty();
//    }
//
//    @Override
//    public List<User> findAll() {
//        return em.createQuery("select u from User u", User.class).getResultList();
//    }
}
