package kr.muve.api;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import kr.muve.common.domain.cart.CartJpaEntity;
import kr.muve.common.domain.category.CategoryJpaEntity;
import kr.muve.common.domain.order.OrderJpaEntity;
import kr.muve.common.domain.orderproduct.OrderProductJpaEntity;
import kr.muve.common.domain.product.ProductJpaEntity;
import kr.muve.common.domain.saved.SavedJpaEntity;
import kr.muve.common.domain.savedProduct.SavedProductJpaEntity;
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
            ProductJpaEntity pro1 = createProduct("김수환무두루미와거북이김수환무두루미와거북이1", 10000L, 100, "https://muve-bucket.s3.ap-northeast-2.amazonaws.com/f67247c2-56fa-4378-aeee-56103b557aed.gif", categoryJpaEntity1);
            em.persist(pro1);

            ProductJpaEntity pro2 = createProduct("JPA2 BOOK", 20000L, 200, "https://muve-bucket.s3.ap-northeast-2.amazonaws.com/f67247c2-56fa-4378-aeee-56103b557aed.gif", categoryJpaEntity2);
            em.persist(pro2);

            ProductJpaEntity pro3 = createProduct("JPA3 BOOK", 30000L, 300, "https://muve-bucket.s3.ap-northeast-2.amazonaws.com/f67247c2-56fa-4378-aeee-56103b557aed.gif", categoryJpaEntity1);
            em.persist(pro3);

            ProductJpaEntity pro4 = createProduct("JPA4 BOOK", 40000L, 400, "https://muve-bucket.s3.ap-northeast-2.amazonaws.com/f67247c2-56fa-4378-aeee-56103b557aed.gif", categoryJpaEntity2);
            em.persist(pro4);

            ProductJpaEntity pro5 = createProduct("JPA5 BOOK", 50000L, 500, "https://muve-bucket.s3.ap-northeast-2.amazonaws.com/f67247c2-56fa-4378-aeee-56103b557aed.gif", categoryJpaEntity1);
            em.persist(pro5);

            ProductJpaEntity pro6 = createProduct("JPA6 BOOK", 60000L, 600, "https://muve-bucket.s3.ap-northeast-2.amazonaws.com/f67247c2-56fa-4378-aeee-56103b557aed.gif", categoryJpaEntity2);
            em.persist(pro6);

            ProductJpaEntity pro7 = createProduct("JPA7 BOOK", 70000L, 700, "https://muve-bucket.s3.ap-northeast-2.amazonaws.com/f67247c2-56fa-4378-aeee-56103b557aed.gif", categoryJpaEntity1);
            em.persist(pro7);

            ProductJpaEntity pro8 = createProduct("김수환무두루미와거북이김수환무두루미와거북이2", 80000L, 800, "https://muve-bucket.s3.ap-northeast-2.amazonaws.com/f67247c2-56fa-4378-aeee-56103b557aed.gif", categoryJpaEntity2);
            em.persist(pro8);

            ProductJpaEntity pro9 = createProduct("JPA9 BOOK", 90000L, 900, "https://muve-bucket.s3.ap-northeast-2.amazonaws.com/f67247c2-56fa-4378-aeee-56103b557aed.gif", categoryJpaEntity1);
            em.persist(pro9);

            ProductJpaEntity pro10 = createProduct("JPA10 BOOK", 100000L, 1000, "https://muve-bucket.s3.ap-northeast-2.amazonaws.com/f67247c2-56fa-4378-aeee-56103b557aed.gif", categoryJpaEntity2);
            em.persist(pro10);

            ProductJpaEntity pro11 = createProduct("JPA11 BOOK", 110000L, 1100, "https://muve-bucket.s3.ap-northeast-2.amazonaws.com/f67247c2-56fa-4378-aeee-56103b557aed.gif", categoryJpaEntity1);
            em.persist(pro11);

            ProductJpaEntity pro12 = createProduct("JPA12 BOOK", 120000L, 1200, "https://muve-bucket.s3.ap-northeast-2.amazonaws.com/f67247c2-56fa-4378-aeee-56103b557aed.gif", categoryJpaEntity2);
            em.persist(pro12);

            ProductJpaEntity pro13 = createProduct("JPA13 BOOK", 130000L, 1300, "https://muve-bucket.s3.ap-northeast-2.amazonaws.com/f67247c2-56fa-4378-aeee-56103b557aed.gif", categoryJpaEntity1);
            em.persist(pro13);

            ProductJpaEntity pro14 = createProduct("JPA14 BOOK", 140000L, 1400, "https://muve-bucket.s3.ap-northeast-2.amazonaws.com/f67247c2-56fa-4378-aeee-56103b557aed.gif", categoryJpaEntity2);
            em.persist(pro14);

            ProductJpaEntity pro15 = createProduct("JPA15 BOOK", 150000L, 1500, "https://muve-bucket.s3.ap-northeast-2.amazonaws.com/f67247c2-56fa-4378-aeee-56103b557aed.gif", categoryJpaEntity1);
            em.persist(pro15);

            ProductJpaEntity pro16 = createProduct("JPA16 BOOK", 160000L, 1600, "https://muve-bucket.s3.ap-northeast-2.amazonaws.com/f67247c2-56fa-4378-aeee-56103b557aed.gif", categoryJpaEntity2);
            em.persist(pro16);

            ProductJpaEntity pro17 = createProduct("JPA17 BOOK", 170000L, 1700, "https://muve-bucket.s3.ap-northeast-2.amazonaws.com/f67247c2-56fa-4378-aeee-56103b557aed.gif", categoryJpaEntity1);
            em.persist(pro17);

            ProductJpaEntity pro18 = createProduct("JPA18 BOOK", 180000L, 1800, "https://muve-bucket.s3.ap-northeast-2.amazonaws.com/f67247c2-56fa-4378-aeee-56103b557aed.gif", categoryJpaEntity2);
            em.persist(pro18);

            ProductJpaEntity pro19 = createProduct("JPA19 BOOK", 190000L, 1900, "https://muve-bucket.s3.ap-northeast-2.amazonaws.com/f67247c2-56fa-4378-aeee-56103b557aed.gif", categoryJpaEntity1);
            em.persist(pro19);

            ProductJpaEntity pro20 = createProduct("JPA20 BOOK", 200000L, 2000, "https://muve-bucket.s3.ap-northeast-2.amazonaws.com/f67247c2-56fa-4378-aeee-56103b557aed.gif", categoryJpaEntity2);
            em.persist(pro20);


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