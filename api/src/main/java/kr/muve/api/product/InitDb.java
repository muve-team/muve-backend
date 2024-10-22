package kr.muve.api.product;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import kr.muve.common.domain.category.CategoryJpaEntity;
import kr.muve.common.domain.order.OrderJpaEntity;
import kr.muve.common.domain.orderproduct.OrderProductJpaEntity;
import kr.muve.common.domain.product.ProductJpaEntity;
import kr.muve.common.domain.saved.SavedJpaEntity;
import kr.muve.common.domain.savedProduct.SavedProductJpaEntity;
import kr.muve.common.domain.user.UserJpaEntity;
import kr.muve.common.service.user.UserJoinDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static kr.muve.common.domain.product.ProductJpaEntity.createProduct;
import static kr.muve.common.domain.user.UserJpaEntity.createUser;

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
            UserJpaEntity userJpaEntity = createUser(dto);
            em.persist(userJpaEntity);

            // 카테고리
// 카테고리 엔티티 생성 및 데이터 저장
            CategoryJpaEntity categoryJpaEntity1 = CategoryJpaEntity.createCategory(
                    "의류",
                    "clothing",
                    "https://images.unsplash.com/photo-1523381210434-271e8be1f52b?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8Y2xvdGhpbmd8ZW58MHx8MHx8fDA%3D"
            );
            em.persist(categoryJpaEntity1);

            CategoryJpaEntity categoryJpaEntity2 = CategoryJpaEntity.createCategory(
                    "전자기기",
                    "electronics",
                    "https://images.unsplash.com/photo-1498049794561-7780e7231661?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8ZWxlY3Ryb25pY3N8ZW58MHx8MHx8fDA%3D"
            );
            em.persist(categoryJpaEntity2);

            CategoryJpaEntity categoryJpaEntity3 = CategoryJpaEntity.createCategory(
                    "식품",
                    "food",
                    "https://images.unsplash.com/photo-1506617420156-8e4536971650?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8Z3JvY2VyaWVzfGVufDB8fDB8fHww"
            );
            em.persist(categoryJpaEntity3);

            CategoryJpaEntity categoryJpaEntity4 = CategoryJpaEntity.createCategory(
                    "가구",
                    "furniture",
                    "https://images.unsplash.com/photo-1538688525198-9b88f6f53126?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8ZnVybml0dXJlfGVufDB8fDB8fHww"
            );
            em.persist(categoryJpaEntity4);

            CategoryJpaEntity categoryJpaEntity5 = CategoryJpaEntity.createCategory(
                    "도서",
                    "books",
                    "https://images.unsplash.com/photo-1495446815901-a7297e633e8d?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8Ym9va3N8ZW58MHx8MHx8fDA%3D"
            );
            em.persist(categoryJpaEntity5);

            CategoryJpaEntity categoryJpaEntity6 = CategoryJpaEntity.createCategory(
                    "스포츠",
                    "sports",
                    "https://images.unsplash.com/photo-1461896836934-ffe607ba8211?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8c3BvcnRzfGVufDB8fDB8fHww"
            );
            em.persist(categoryJpaEntity6);

            CategoryJpaEntity categoryJpaEntity7 = CategoryJpaEntity.createCategory(
                    "뷰티",
                    "beauty",
                    "https://images.unsplash.com/photo-1596462502278-27bfdc403348?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8YmVhdXR5JTIwcHJvZHVjdHN8ZW58MHx8MHx8fDA%3D"
            );
            em.persist(categoryJpaEntity7);

            CategoryJpaEntity categoryJpaEntity8 = CategoryJpaEntity.createCategory(
                    "자동차",
                    "automotive",
                    "https://images.unsplash.com/photo-1494976388531-d1058494cdd8?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8Y2FyfGVufDB8fDB8fHww"
            );
            em.persist(categoryJpaEntity8);


            // 상품
            ProductJpaEntity pro1 = createProduct("JPA1 BOOK", 10000L, 100, categoryJpaEntity1);
            em.persist(pro1);

            ProductJpaEntity pro2 = createProduct("JPA2 BOOK", 20000L, 200, categoryJpaEntity2);
            em.persist(pro2);

            ProductJpaEntity pro3 = createProduct("JPA3 BOOK", 30000L, 300, categoryJpaEntity1);
            em.persist(pro3);

            ProductJpaEntity pro4 = createProduct("JPA4 BOOK", 40000L, 400, categoryJpaEntity2);
            em.persist(pro4);

            ProductJpaEntity pro5 = createProduct("JPA5 BOOK", 50000L, 500, categoryJpaEntity1);
            em.persist(pro5);

            ProductJpaEntity pro6 = createProduct("JPA6 BOOK", 60000L, 600, categoryJpaEntity2);
            em.persist(pro6);

            ProductJpaEntity pro7 = createProduct("JPA7 BOOK", 70000L, 700, categoryJpaEntity1);
            em.persist(pro7);

            ProductJpaEntity pro8 = createProduct("JPA8 BOOK", 80000L, 800, categoryJpaEntity2);
            em.persist(pro8);

            ProductJpaEntity pro9 = createProduct("JPA9 BOOK", 90000L, 900, categoryJpaEntity1);
            em.persist(pro9);

            ProductJpaEntity pro10 = createProduct("JPA10 BOOK", 100000L, 1000, categoryJpaEntity2);
            em.persist(pro10);

            ProductJpaEntity pro11 = createProduct("JPA11 BOOK", 110000L, 1100, categoryJpaEntity1);
            em.persist(pro11);

            ProductJpaEntity pro12 = createProduct("JPA12 BOOK", 120000L, 1200, categoryJpaEntity2);
            em.persist(pro12);

            ProductJpaEntity pro13 = createProduct("JPA13 BOOK", 130000L, 1300, categoryJpaEntity1);
            em.persist(pro13);

            ProductJpaEntity pro14 = createProduct("JPA14 BOOK", 140000L, 1400, categoryJpaEntity2);
            em.persist(pro14);

            ProductJpaEntity pro15 = createProduct("JPA15 BOOK", 150000L, 1500, categoryJpaEntity1);
            em.persist(pro15);

            ProductJpaEntity pro16 = createProduct("JPA16 BOOK", 160000L, 1600, categoryJpaEntity2);
            em.persist(pro16);

            ProductJpaEntity pro17 = createProduct("JPA17 BOOK", 170000L, 1700, categoryJpaEntity1);
            em.persist(pro17);

            ProductJpaEntity pro18 = createProduct("JPA18 BOOK", 180000L, 1800, categoryJpaEntity2);
            em.persist(pro18);

            ProductJpaEntity pro19 = createProduct("JPA19 BOOK", 190000L, 1900, categoryJpaEntity1);
            em.persist(pro19);

            ProductJpaEntity pro20 = createProduct("JPA20 BOOK", 200000L, 2000, categoryJpaEntity2);
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

        }

    }
}