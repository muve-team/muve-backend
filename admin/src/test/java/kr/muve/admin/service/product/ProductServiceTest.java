package kr.muve.admin.service.product;

import jakarta.persistence.EntityManager;
import kr.muve.admin.domain.product.Product;
import kr.muve.common.service.product.ProductDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Autowired
    EntityManager em;

    @Test
    void 상품등록() {
        // given
        ProductDto dto = new ProductDto(null, "1", 1000L, 1000);

        // when
        Long id = productService.create(dto);
        Product foundProduct = em.find(Product.class, 1L);

        // then
        assertThat(foundProduct.getId()).isEqualTo(id);
    }

    @Test
    void 상품수정() {
        // given
        Product product = Product.createProduct("1", 1000L, 1000);
        em.persist(product);

        List<Product> products = em.createQuery("select p from Product p", Product.class).getResultList();

        ProductDto update = new ProductDto(1L, "2", 2000L, 2000);

        // when
        productService.update(update);
        Product foundProduct = em.find(Product.class, 1L);

        // then
        assertThat(foundProduct.getName()).isEqualTo(update.getName());
        assertThat(foundProduct.getPrice()).isEqualTo(update.getPrice());
        assertThat(foundProduct.getStockQuantity()).isEqualTo(update.getStockQuantity());
    }

}