package kr.jinsang.sshop;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import kr.jinsang.sshop.domain.order.Order;
import kr.jinsang.sshop.domain.orderproduct.OrderProduct;
import kr.jinsang.sshop.domain.product.Product;
import kr.jinsang.sshop.domain.user.Address;
import kr.jinsang.sshop.domain.user.User;
import kr.jinsang.sshop.service.user.UserJoinDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static kr.jinsang.sshop.domain.product.Product.createProduct;
import static kr.jinsang.sshop.domain.user.User.createUser;

@Component
@RequiredArgsConstructor
@Profile("!test")
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {
            UserJoinDto dto = new UserJoinDto("userA", "a@naver.com", "1234",
                    "010-1111-1111", "A", "B", "C");
            User user = createUser(dto);
            em.persist(user);

            Product pro1 = createProduct("JPA1 BOOK", 10000L, 100);
            em.persist(pro1);

            Product pro2 = createProduct("JPA2 BOOK", 20000L, 200);
            em.persist(pro2);

            OrderProduct orderProduct1 = OrderProduct.createOrderProduct(pro1, 1);
            OrderProduct orderProduct2 = OrderProduct.createOrderProduct(pro2, 2);

            Order order = Order.createOrder(user, orderProduct1, orderProduct2);
            em.persist(order);
        }

    }
}

