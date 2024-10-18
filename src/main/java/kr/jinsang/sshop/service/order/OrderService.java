package kr.jinsang.sshop.service.order;

import kr.jinsang.sshop.domain.order.Order;
import kr.jinsang.sshop.domain.orderproduct.OrderProduct;
import kr.jinsang.sshop.domain.user.User;
import kr.jinsang.sshop.domain.product.Product;
import kr.jinsang.sshop.repository.order.OrderRepository;
import kr.jinsang.sshop.repository.order.OrderSearch;
import kr.jinsang.sshop.repository.product.ProductRepository;
import kr.jinsang.sshop.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Long create(OrderDto dto) {

        Long userId = dto.getUserId();
        Long productId = dto.getProductId();

        User user = userRepository.findOne(userId);
        Product product = productRepository.findOne(productId);

        OrderProduct orderProduct = OrderProduct.createOrderProduct(product, dto.getCount());

        Order order = Order.createOrder(user, orderProduct);
        orderRepository.save(order);

        return order.getId();
    }

    // 주문 목록 조회
    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAll(orderSearch);
    }

    @Transactional
    public void cancelOrder(Long id) {
        Order order = orderRepository.findOne(id);
        orderRepository.delete(order);
    }

}
