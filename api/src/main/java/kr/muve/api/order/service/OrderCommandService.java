package kr.muve.api.order.service;

import com.google.common.base.Preconditions;
import jakarta.servlet.http.HttpServletRequest;
import kr.muve.api.product.service.ProductQueryService;
import kr.muve.api.user.service.UserQueryService;
import kr.muve.common.domain.delivery.DeliveryJpaEntity;
import kr.muve.common.domain.order.OrderJpaEntity;
import kr.muve.common.domain.order.product.OrderProductJpaEntity;
import kr.muve.common.domain.product.ProductJpaEntity;
import kr.muve.common.domain.user.UserJpaEntity;
import kr.muve.common.repository.delivery.SpringDataDeliveryRepository;
import kr.muve.common.repository.order.SpringDataOrderRepository;
import kr.muve.common.security.JweTokenProvider;
import kr.muve.common.service.order.CreateOrder;
import kr.muve.common.service.order.OrderCreateCommand;
import kr.muve.common.util.CookieUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static kr.muve.common.security.JweTokenProvider.AUTH_TOKEN;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderCommandService implements CreateOrder {

    private final SpringDataOrderRepository orderRepository;
    private final ProductQueryService productQueryService;
    private final UserQueryService userQueryService;
    private final JweTokenProvider jweTokenProvider;
    private final SpringDataDeliveryRepository deliveryRepository;

    @Override
    public Long create(OrderCreateCommand command, HttpServletRequest request) {
        String token = jweTokenProvider.resolveToken(request);

        Preconditions.checkArgument(jweTokenProvider.validToken(token));

        if (StringUtils.isBlank(token)) {
            return -1L;
        }

        String email = jweTokenProvider.getEmail(token);
        UserJpaEntity foundUser = userQueryService.getUserByEmail(email);

        ProductJpaEntity foundProduct = productQueryService.getProductById(command.productId());
        foundProduct.checkPreCondition(command.count());

        OrderProductJpaEntity orderProduct = OrderProductJpaEntity.createOrderProduct(foundProduct, command.count());
        DeliveryJpaEntity delivery = DeliveryJpaEntity.createDelivery(command);
        deliveryRepository.save(delivery);


        OrderJpaEntity orderJpaEntity = OrderJpaEntity.createOrder(command, delivery, foundUser, orderProduct);
        OrderJpaEntity savedOrder = orderRepository.saveAndFlush(orderJpaEntity);

        return savedOrder.getId();
    }
}
