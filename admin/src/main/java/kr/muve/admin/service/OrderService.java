package kr.muve.admin.service;

import kr.muve.common.domain.order.OrderJpaEntity;
import kr.muve.common.domain.order.product.OrderProductJpaEntity;
import kr.muve.common.domain.product.ProductJpaEntity;
import kr.muve.common.domain.user.UserJpaEntity;
import kr.muve.common.exception.OrderNotFoundException;
import kr.muve.common.repository.order.OrderSearch;
import kr.muve.common.repository.order.SpringDataOrderRepository;
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

    private final SpringDataOrderRepository orderRepository;
    private final UserService userService;
    private final ProductService productService;

    @Override
    @Transactional
    public Long create(OrderDto dto) {

        Long userId = dto.getUserId();
        Long productId = dto.getProductId();

        UserJpaEntity userJpaEntity = userService.findById(userId);
        ProductJpaEntity productJpaEntity = productService.findById(productId);

        OrderProductJpaEntity orderProductJpaEntity = OrderProductJpaEntity.createOrderProduct(productJpaEntity, dto.getCount());

        OrderJpaEntity orderJpaEntity = OrderJpaEntity.createOrder(userJpaEntity, orderProductJpaEntity);
        orderRepository.save(orderJpaEntity);

        return orderJpaEntity.getId();
    }

    // 주문 목록 조회
    @Override
    public List<OrderJpaEntity> findOrders(OrderSearch orderSearch) {
        return orderRepository.findDynamicOrders(orderSearch);
    }

    public OrderJpaEntity findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("주문을 찾을 수 없습니다."));
    }

    @Override
    @Transactional
    public void cancelOrder(Long id) {
        // order status 변경
        OrderJpaEntity orderJpaEntity = findById(id);
        orderJpaEntity.cancelOrder();

        // product count 원복
        List<OrderProductJpaEntity> orderProductJpaEntities = orderJpaEntity.getOrderProductJpaEntities();
        orderProductJpaEntities.forEach(op -> op.getProductJpaEntity().addStock(op.getCount()));
    }

}
