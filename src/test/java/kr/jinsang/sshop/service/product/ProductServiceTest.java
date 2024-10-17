//package kr.jinsang.sshop.service.product;
//
//import jakarta.persistence.EntityManager;
//import kr.jinsang.sshop.domain.product.Product;
//import kr.jinsang.sshop.domain.user.User;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//@SpringBootTest
//@Transactional
//class ProductServiceTest {
//
//    @Autowired
//    ProductService productService;
//
//    @Autowired
//    EntityManager em;
//
//    @Test
//    void 상품등록() {
//        // given
//        ProductDto dto = new ProductDto(null, "1", 1000L, 1000);
//
//        // when
//        Long id = productService.create(dto);
//        Product foundProduct = em.find(Product.class, 1L);
//
//        // then
//        assertThat(foundProduct.getId()).isEqualTo(id);
//    }
//
//    @Test
//    void 상품수정() {
//        // given
//        Product product = Product.createProduct("1", 1000L, 1000);
//        em.persist(product);
//
//        ProductDto update = new ProductDto(1L, "2", 2000L, 2000);
//
//        // when
//        productService.update(update);
//        Product foundProduct = em.find(Product.class, 1L);
//
//        // then
//        assertThat(foundProduct.getName()).isEqualTo(update.getName());
//        assertThat(foundProduct.getPrice()).isEqualTo(update.getPrice());
//        assertThat(foundProduct.getStockQuantity()).isEqualTo(update.getStockQuantity());
//    }
//
//}