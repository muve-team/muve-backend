package kr.jinsang.sshop.service.order;

import jakarta.persistence.EntityManager;
import kr.jinsang.sshop.domain.order.Order;
import kr.jinsang.sshop.domain.orderproduct.OrderProduct;
import kr.jinsang.sshop.domain.product.Product;
import kr.jinsang.sshop.domain.user.User;
import kr.jinsang.sshop.service.product.ProductDto;
import kr.jinsang.sshop.service.product.ProductService;
import kr.jinsang.sshop.service.user.UserJoinDto;
import kr.jinsang.sshop.service.user.UserService;
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
    void 주문등록() {
        // given
        UserJoinDto userDto = new UserJoinDto("a", "a@naver.com", "1234", "010-1111-1111",
                "a동", "3001호", "2345");
        userService.join(userDto);
        User foundUser = em.find(User.class, 1L);

        ProductDto productDto = new ProductDto(null, "1", 1000L, 10);
        productService.create(productDto);
        Product foundProduct = em.find(Product.class, 1L);

        OrderDto orderDto = new OrderDto(foundUser.getId(), foundProduct.getId(), 1);

        // when
        Long orderId = orderService.create(orderDto);
        Order foundOrder = em.find(Order.class, 1L);

        // then
        assertThat(orderId).isEqualTo(foundOrder.getId());

    }

    @Test
    void 주문취소() {
        // given
        UserJoinDto userDto = new UserJoinDto("a", "a@naver.com", "1234", "010-1111-1111",
                "a동", "3001호", "2345");
        userService.join(userDto);
        User foundUser = em.find(User.class, 1L);

        ProductDto productDto = new ProductDto(null, "1", 1000L, 10);
        productService.create(productDto);
        Product foundProduct = em.find(Product.class, 1L);

        OrderProduct orderProduct = OrderProduct.createOrderProduct(foundProduct, 1);
        Order order = Order.createOrder(foundUser, orderProduct);
        em.persist(order);
        Long orderId = order.getId();


        // when
        orderService.cancelOrder(orderId);
        Order foundOrder = em.find(Order.class, orderId);

        // then
        assertNull(foundOrder, "test success");
    }
}
