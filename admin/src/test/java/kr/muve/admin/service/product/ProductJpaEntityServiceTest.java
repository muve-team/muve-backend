package kr.muve.admin.service.product;

import jakarta.persistence.EntityManager;
import kr.muve.admin.service.ProductService;
import kr.muve.common.domain.category.CategoryJpaEntity;
import kr.muve.common.domain.product.ProductJpaEntity;
import kr.muve.common.service.product.ProductDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ProductJpaEntityServiceTest {

    @Autowired
    ProductService productService;

    @Autowired
    EntityManager em;

    @Test
    void 상품_수정() {
        // given
        CategoryJpaEntity categoryJpaEntity = CategoryJpaEntity.createCategory("책", "book");
        em.persist(categoryJpaEntity);
        ProductJpaEntity productJpaEntity = ProductJpaEntity.createProduct("1", 1000L, 1000, categoryJpaEntity);
        em.persist(productJpaEntity);

        Long categoryId = categoryJpaEntity.getId();
        ProductDto update = new ProductDto(1L, "2", 2000L, 2000, categoryId, "책", "book");

        // when
        productService.update(update);
        ProductJpaEntity foundProductJpaEntity = em.find(ProductJpaEntity.class, 1L);

        // then
        assertThat(foundProductJpaEntity.getName()).isEqualTo(update.getName());
        assertThat(foundProductJpaEntity.getPrice()).isEqualTo(update.getPrice());
        assertThat(foundProductJpaEntity.getStockQuantity()).isEqualTo(update.getStockQuantity());
        assertThat(foundProductJpaEntity.getCategoryJpaEntity().getKoreanName()).isEqualTo(update.getCategoryKoreanName());
        assertThat(foundProductJpaEntity.getCategoryJpaEntity().getEnglishName()).isEqualTo(update.getCategoryEnglishName());
    }

}