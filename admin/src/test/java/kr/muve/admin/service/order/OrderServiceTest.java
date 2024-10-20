package kr.muve.admin.service.order;

import jakarta.persistence.EntityManager;
import kr.muve.admin.service.OrderService;
import kr.muve.admin.service.ProductService;
import kr.muve.admin.service.UserService;
import kr.muve.common.domain.category.Category;
import kr.muve.common.domain.order.Order;
import kr.muve.common.domain.order.OrderStatus;
import kr.muve.common.domain.orderproduct.OrderProduct;
import kr.muve.common.domain.product.Product;
import kr.muve.common.domain.user.User;
import kr.muve.common.service.order.OrderDto;
import kr.muve.common.service.product.ProductDto;
import kr.muve.common.service.user.UserJoinDto;
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
public class OrderServiceTest {

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
        User foundUser = createUser();
        Category category = createCategory();
        Product foundProduct = createProduct(category);
        OrderDto orderDto = new OrderDto(foundUser.getId(), foundProduct.getId(), 1);

        // when
        Long orderId = orderService.create(orderDto);
        Order foundOrder = em.find(Order.class, 1L);

        // then
        assertThat(orderId).isEqualTo(foundOrder.getId());

    }

    @Test
    void 주문_취소() {
        // given
        Order order = createOrder();
        Long orderId = order.getId();

        // when
        orderService.cancelOrder(orderId);
        Order foundOrder = em.find(Order.class, orderId);
        Product product = foundOrder.getOrderProducts().get(0).getProduct();

        // then
        assertThat(foundOrder.getStatus()).isEqualTo(OrderStatus.CANCEL);
        assertThat(product.getStockQuantity()).isEqualTo(10);
    }

    Order createOrder() {
        User foundUser = createUser();
        Category category = createCategory();
        Product foundProduct = createProduct(category);
        OrderProduct orderProduct = OrderProduct.createOrderProduct(foundProduct, 1);

        Order order = Order.createOrder(foundUser, orderProduct);
        em.persist(order);

        return order;
    }

    private User createUser() {
        UserJoinDto userDto = new UserJoinDto("a", "a@naver.com", "1234", "010-1111-1111",
                "a동", "3001호", "2345");
        userService.join(userDto);
        User user = em.find(User.class, 1L);
        return user;
    }

    private Category createCategory() {
        Category category = Category.createCategory("책", "book");
        em.persist(category);
        return category;
    }

    private Product createProduct(Category category) {
        ProductDto productDto = new ProductDto(null, "1", 1000L, 10,
                category.getId(), category.getKoreanName(), category.getEnglishName());
        productService.create(productDto);
        Product foundProduct = em.find(Product.class, 1L);
        return foundProduct;
    }
}