package kr.muve.admin;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import kr.muve.common.domain.category.Category;
import kr.muve.common.domain.order.Order;
import kr.muve.common.domain.orderproduct.OrderProduct;
import kr.muve.common.domain.product.Product;
import kr.muve.common.domain.user.User;
import kr.muve.common.service.user.UserJoinDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static kr.muve.common.domain.product.Product.createProduct;
import static kr.muve.common.domain.user.User.createUser;

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
            // 사용자
            UserJoinDto dto = new UserJoinDto("userA", "a@naver.com", "1234",
                    "010-1111-1111", "A", "B", "C");
            User user = createUser(dto);
            em.persist(user);

            // 카테고리
            Category category1 = Category.createCategory("전자기기", "electronic");
            em.persist(category1);

            Category category2 = Category.createCategory("책", "book");
            em.persist(category2);

            // 상품
            Product pro1 = createProduct("JPA1 BOOK", 10000L, 100, category1);
            em.persist(pro1);

            Product pro2 = createProduct("JPA2 BOOK", 20000L, 200, category2);
            em.persist(pro2);

            // 주문 상품
            OrderProduct orderProduct1 = OrderProduct.createOrderProduct(pro1, 1);
            OrderProduct orderProduct2 = OrderProduct.createOrderProduct(pro2, 2);

            // 주문
            Order order = Order.createOrder(user, orderProduct1, orderProduct2);
            em.persist(order);
        }

    }
}