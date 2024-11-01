package kr.muve.admin;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import kr.muve.common.domain.category.CategoryJpaEntity;
import kr.muve.common.domain.order.OrderJpaEntity;
import kr.muve.common.domain.orderProduct.OrderProductJpaEntity;
import kr.muve.common.domain.product.ProductJpaEntity;
import kr.muve.common.domain.user.UserJpaEntity;
import kr.muve.common.service.user.UserJoinCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static kr.muve.common.domain.product.ProductJpaEntity.createProduct;
import static kr.muve.common.domain.user.UserJpaEntity.createUser;

@Component
@RequiredArgsConstructor
@Profile("local")
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
            UserJoinCommand dto = new UserJoinCommand("userA", "a@naver.com", "1234",
                    "010-1111-1111", "A", "B", "C");
            UserJpaEntity userJpaEntity = createUser(dto);
            em.persist(userJpaEntity);

            // 카테고리
            CategoryJpaEntity categoryJpaEntity1 = CategoryJpaEntity.createCategory("전자기기", "electronic", "1.jpg");
            em.persist(categoryJpaEntity1);

            CategoryJpaEntity categoryJpaEntity2 = CategoryJpaEntity.createCategory("책", "book", "1.jpg");
            em.persist(categoryJpaEntity2);

            // 상품
            ProductJpaEntity pro1 = createProduct("JPA1 BOOK", 10000L, 100, "url", categoryJpaEntity1);
            em.persist(pro1);

            ProductJpaEntity pro2 = createProduct("JPA2 BOOK", 20000L, 200, "url", categoryJpaEntity2);
            em.persist(pro2);

            // 주문 상품
            OrderProductJpaEntity orderProductJpaEntity1 = OrderProductJpaEntity.createOrderProduct(pro1, 1);
            OrderProductJpaEntity orderProductJpaEntity2 = OrderProductJpaEntity.createOrderProduct(pro2, 2);

            // 주문
            OrderJpaEntity orderJpaEntity = OrderJpaEntity.createOrder(userJpaEntity, orderProductJpaEntity1, orderProductJpaEntity2);
            em.persist(orderJpaEntity);
        }

    }
}