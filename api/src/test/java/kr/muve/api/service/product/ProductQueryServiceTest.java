//package kr.muve.api.service.product;
//
//import jakarta.persistence.EntityManager;
//import kr.muve.api.ApiApplication;
//import kr.muve.api.product.service.ProductQueryService;
//import kr.muve.common.domain.category.CategoryJpaEntity;
//import kr.muve.common.domain.product.ProductJpaEntity;
//import kr.muve.common.service.product.ProductDetailRes;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest(classes = ApiApplication.class)
//@Transactional
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//public class ProductQueryServiceTest {
//
//    @Autowired
//    ProductQueryService productQueryService;
//
//    @Autowired
//    EntityManager em;
//
//    @Test
//    void 상품_상세() {
//        // given
//        CategoryJpaEntity categoryJpaEntity = CategoryJpaEntity.createCategory("책", "book");
//        em.persist(categoryJpaEntity);
//        ProductJpaEntity product = ProductJpaEntity.createProduct("1", 1000L, 1000, categoryJpaEntity);
//        em.persist(product);
//
//        // when
//        ProductDetailRes detailRes = productQueryService.getProductDetail(1L);
//
//        // then
//        assertThat(product.getId()).isEqualTo(detailRes.getProductId());
//
//
//    }
//}
