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

        em.flush(); // insert
        em.clear();

        // 쿼리 나감

        Order foundOrder = em.find(Order.class, 1L);
        System.out.println(foundOrder.getUser());


    }

}