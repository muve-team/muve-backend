package kr.jinsang.sshop.repository.user;

import jakarta.persistence.EntityManager;
import kr.jinsang.sshop.domain.user.User;
import kr.jinsang.sshop.service.user.UserJoinDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public void save(User user) {
        em.persist(user);
    }
    
    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class)
                .getResultList();
    }
    
    public User findOne(Long id) {
        return em.find(User.class, id);
    }
}
