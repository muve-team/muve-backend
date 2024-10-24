package kr.muve.admin.service.order;

import jakarta.persistence.EntityManager;
import kr.muve.admin.service.OrderService;
import kr.muve.admin.service.ProductService;
import kr.muve.admin.service.UserService;
import kr.muve.common.domain.category.CategoryJpaEntity;
import kr.muve.common.domain.order.OrderJpaEntity;
import kr.muve.common.domain.order.OrderStatus;
import kr.muve.common.domain.orderproduct.OrderProductJpaEntity;
import kr.muve.common.domain.product.ProductJpaEntity;
import kr.muve.common.domain.user.UserJpaEntity;
import kr.muve.common.service.order.OrderDto;
import kr.muve.common.service.product.ProductDto;
import kr.muve.common.service.user.UserJoinCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class OrderJpaEntityServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;


    @Autowired
    EntityManager em;

    @Test
    void 주문_등록() {
        // given
        UserJpaEntity foundUserJpaEntity = createUser();
        CategoryJpaEntity categoryJpaEntity = createCategory();
        ProductJpaEntity foundProductJpaEntity = createProduct(categoryJpaEntity);
        OrderDto orderDto = new OrderDto(foundUserJpaEntity.getId(), foundProductJpaEntity.getId(), 1);

        // when
        Long orderId = orderService.create(orderDto);
        OrderJpaEntity foundOrderJpaEntity = em.find(OrderJpaEntity.class, 1L);

        // then
        assertThat(orderId).isEqualTo(foundOrderJpaEntity.getId());

    }

    @Test
    void 주문_취소() {
        // given
        OrderJpaEntity orderJpaEntity = createOrder();
        Long orderId = orderJpaEntity.getId();

        // when
        orderService.cancelOrder(orderId);
        OrderJpaEntity foundOrderJpaEntity = em.find(OrderJpaEntity.class, orderId);
        ProductJpaEntity productJpaEntity = foundOrderJpaEntity.getOrderProductJpaEntities().get(0).getProductJpaEntity();

        // then
        assertThat(foundOrderJpaEntity.getStatus()).isEqualTo(OrderStatus.CANCEL);
        assertThat(productJpaEntity.getStockQuantity()).isEqualTo(10);
    }

    OrderJpaEntity createOrder() {
        UserJpaEntity foundUserJpaEntity = createUser();
        CategoryJpaEntity categoryJpaEntity = createCategory();
        ProductJpaEntity foundProductJpaEntity = createProduct(categoryJpaEntity);
        OrderProductJpaEntity orderProductJpaEntity = OrderProductJpaEntity.createOrderProduct(foundProductJpaEntity, 1);

        OrderJpaEntity orderJpaEntity = OrderJpaEntity.createOrder(foundUserJpaEntity, orderProductJpaEntity);
        em.persist(orderJpaEntity);

        return orderJpaEntity;
    }

    private UserJpaEntity createUser() {
        UserJoinCommand userDto = new UserJoinCommand("a", "a@naver.com", "1234", "010-1111-1111",
                "a동", "3001호", "2345");
        userService.join(userDto);
        UserJpaEntity userJpaEntity = em.find(UserJpaEntity.class, 1L);
        return userJpaEntity;
    }

    private CategoryJpaEntity createCategory() {
        CategoryJpaEntity categoryJpaEntity = CategoryJpaEntity.createCategory("책", "book", "1.jpg");
        em.persist(categoryJpaEntity);
        return categoryJpaEntity;
    }

    private ProductJpaEntity createProduct(CategoryJpaEntity categoryJpaEntity) {
        ProductDto productDto = new ProductDto(null, "1", 1000L, 10,
                categoryJpaEntity.getId(), categoryJpaEntity.getName(), categoryJpaEntity.getSlug());
        productService.create(productDto);
        ProductJpaEntity foundProductJpaEntity = em.find(ProductJpaEntity.class, 1L);
        return foundProductJpaEntity;
    }
}