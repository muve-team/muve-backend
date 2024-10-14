package kr.jinsang.sshop.domain.order;

import jakarta.persistence.EntityManager;
import kr.jinsang.sshop.domain.user.Address;
import kr.jinsang.sshop.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderTest {

    @Autowired EntityManager em;

    @Test
    void test() {

        User user = new User("1", "2", "3",
                "4", new Address("1", "2", "3"));

        em.persist(user);

        Order order = new Order(OrderStatus.ORDER, LocalDateTime.now(), user);

        em.persist(order);

        System.out.println(order);

        em.flush(); // insert 쿼리 나감
        em.clear();

        // 오더만 가져오기
        Order foundOrder = em.find(Order.class, 1L);
        System.out.println(foundOrder.getUser().getClass());
        
        em.flush();
        em.clear();

        // 패치 조인 (Order 가져올 때 User 가져오기)
        Order foundOrder2 = em.createQuery("select o from Order o join fetch o.user u where o.id = 1", Order.class).getSingleResult();
        System.out.println(foundOrder2);

    }

}