package kr.muve.api.order.service;

import jakarta.servlet.http.HttpServletRequest;
import kr.muve.api.user.service.UserQueryService;
import kr.muve.common.domain.order.OrderJpaEntity;
import kr.muve.common.domain.user.UserJpaEntity;
import kr.muve.common.exception.BaseException;
import kr.muve.common.exception.ErrorCode;
import kr.muve.common.exception.JweTokenInvalidException;
import kr.muve.common.repository.order.SpringDataOrderRepository;
import kr.muve.common.security.JweTokenProvider;
import kr.muve.common.service.order.DetailOrder;
import kr.muve.common.service.order.OrderDetailRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderQueryService implements DetailOrder {

    private final SpringDataOrderRepository orderRepository;
    private final JweTokenProvider jweTokenProvider;
    private final UserQueryService userQueryService;

    @Override
    public OrderDetailRes getOrderDetail(Long orderId, HttpServletRequest request) {
        OrderJpaEntity foundOrder = getOrderById(orderId);

        return OrderDetailRes.from(foundOrder);
    }

    public OrderJpaEntity getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new BaseException(ErrorCode.ORDER_NOT_FOUND));
    }
}
