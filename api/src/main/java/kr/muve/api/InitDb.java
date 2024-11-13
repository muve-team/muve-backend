package kr.muve.api;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import kr.muve.common.domain.cart.CartJpaEntity;
import kr.muve.common.domain.category.CategoryJpaEntity;
import kr.muve.common.domain.order.OrderJpaEntity;
import kr.muve.common.domain.order.product.OrderProductJpaEntity;
import kr.muve.common.domain.product.ProductJpaEntity;
import kr.muve.common.domain.saved.SavedJpaEntity;
import kr.muve.common.domain.saved.product.SavedProductJpaEntity;
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
            UserJoinCommand dto = new UserJoinCommand("userA", "test@test.com", "123456",
                    "010-1111-1111", "A", "B", "C");
            UserJpaEntity userJpaEntity = createUser(dto);
            em.persist(userJpaEntity);

            // 카테고리
            // 카테고리 엔티티 생성 및 데이터 저장
            CategoryJpaEntity categoryJpaEntity1 = CategoryJpaEntity.createCategory(
                    "의류",
                    "clothing",
                    "fluent:clothes-hanger-24-regular"
            );
            em.persist(categoryJpaEntity1);

            // 전자기기
            CategoryJpaEntity categoryJpaEntity2 = CategoryJpaEntity.createCategory(
                    "전자기기",
                    "electronics",
                    "f7:device-laptop"
            );
            em.persist(categoryJpaEntity2);

            // 식품
            CategoryJpaEntity categoryJpaEntity3 = CategoryJpaEntity.createCategory(
                    "식품",
                    "food",
                    "ep:food"
            );
            em.persist(categoryJpaEntity3);

            // 가구
            CategoryJpaEntity categoryJpaEntity4 = CategoryJpaEntity.createCategory(
                    "가구",
                    "furniture",
                    "mdi:table-furniture"
            );
            em.persist(categoryJpaEntity4);

            // 도서
            CategoryJpaEntity categoryJpaEntity5 = CategoryJpaEntity.createCategory(
                    "도서",
                    "books",
                    "fluent:book-24-regular"
            );
            em.persist(categoryJpaEntity5);

            // 스포츠
            CategoryJpaEntity categoryJpaEntity6 = CategoryJpaEntity.createCategory(
                    "스포츠",
                    "sports",
                    "fluent:sport-24-regular"
            );
            em.persist(categoryJpaEntity6);

            // 뷰티
            CategoryJpaEntity categoryJpaEntity7 = CategoryJpaEntity.createCategory(
                    "뷰티",
                    "beauty",
                    "solar:cosmetic-linear"
            );
            em.persist(categoryJpaEntity7);

            // 자동차
            CategoryJpaEntity categoryJpaEntity8 = CategoryJpaEntity.createCategory(
                    "자동차",
                    "automotive",
                    "pepicons-pencil:car"
            );
            em.persist(categoryJpaEntity8);


            // 상품
            ProductJpaEntity pro1 = createProduct("김수환무두루미와거북이김수환무두루미와거북이1", "JPA", "Kim Suhwanmu Turtle", "JPA", 10000L, 100, "https://muve-bucket.s3.ap-northeast-2.amazonaws.com/f67247c2-56fa-4378-aeee-56103b557aed.gif", categoryJpaEntity1);
            em.persist(pro1);

            ProductJpaEntity pro2 = createProduct("JPA2 책", "JPA", "JPA2 BOOK", "JPA", 20000L, 200, "https://muve-bucket.s3.ap-northeast-2.amazonaws.com/f67247c2-56fa-4378-aeee-56103b557aed.gif", categoryJpaEntity2);
            em.persist(pro2);

            ProductJpaEntity pro3 = createProduct("JPA3 책", "JPA", "JPA3 BOOK", "JPA", 30000L, 300, "https://muve-bucket.s3.ap-northeast-2.amazonaws.com/f67247c2-56fa-4378-aeee-56103b557aed.gif", categoryJpaEntity1);
            em.persist(pro3);

            ProductJpaEntity pro4 = createProduct("JPA4 책", "JPA", "JPA4 BOOK", "JPA", 40000L, 400, "https://muve-bucket.s3.ap-northeast-2.amazonaws.com/f67247c2-56fa-4378-aeee-56103b557aed.gif", categoryJpaEntity2);
            em.persist(pro4);


            // 주문 상품
            OrderProductJpaEntity orderProductJpaEntity1 = OrderProductJpaEntity.createOrderProduct(pro1, 1);
            OrderProductJpaEntity orderProductJpaEntity2 = OrderProductJpaEntity.createOrderProduct(pro2, 2);

            // 주문
            OrderJpaEntity orderJpaEntity = OrderJpaEntity.createOrder(userJpaEntity, orderProductJpaEntity1, orderProductJpaEntity2);
            em.persist(orderJpaEntity);

            // 찜 목록
            SavedJpaEntity saved1 = SavedJpaEntity.createSaved(userJpaEntity);

            SavedProductJpaEntity savedProductJpaEntity1 = SavedProductJpaEntity.createSavedProduct(pro1);
            SavedProductJpaEntity savedProductJpaEntity2 = SavedProductJpaEntity.createSavedProduct(pro2);

            saved1.addSavedProduct(savedProductJpaEntity1);
            saved1.addSavedProduct(savedProductJpaEntity2);

            em.persist(saved1);

            // 장바구니 생성
            CartJpaEntity cart = CartJpaEntity.createCart(userJpaEntity);
            em.persist(cart);
//
//            CartJpaEntity cartAddProduct1 = CartProductJpaEntity.createCartProduct(cart1, pro1, 10);
//            CartJpaEntity cartAddProduct2 = CartProductJpaEntity.createCartProduct(cart1, pro2, 20);
//
//            em.persist(cartAddProduct1);
//            em.persist(cartAddProduct2);

        }

    }
}