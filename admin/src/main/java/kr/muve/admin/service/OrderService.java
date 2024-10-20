package kr.muve.admin.service;

import kr.muve.common.domain.order.Order;
import kr.muve.common.domain.orderproduct.OrderProduct;
import kr.muve.common.domain.product.Product;
import kr.muve.common.domain.user.User;
import kr.muve.common.repository.order.OrderRepository;
import kr.muve.common.repository.order.OrderSearch;
import kr.muve.common.repository.product.ProductRepository;
import kr.muve.common.repository.user.UserRepository;
import kr.muve.common.service.order.CancelOrder;
import kr.muve.common.service.order.CreateOrder;
import kr.muve.common.service.order.FindOrders;
import kr.muve.common.service.order.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService implements CreateOrder, CancelOrder, FindOrders {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
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
    @Override
    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAll(orderSearch);
    }

    @Override
    @Transactional
    public void cancelOrder(Long id) {
        Order order = orderRepository.findOne(id);
        orderRepository.delete(order);
    }

}
