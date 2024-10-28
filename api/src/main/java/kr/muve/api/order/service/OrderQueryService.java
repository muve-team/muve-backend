package kr.muve.api.order.service;

import kr.muve.common.domain.order.OrderJpaEntity;
import kr.muve.common.exception.BaseException;
import kr.muve.common.exception.ErrorCode;
import kr.muve.common.exception.OrderNotFoundException;
import kr.muve.common.repository.order.OrderRepositoryCustom;
import kr.muve.common.repository.order.OrderRepositoryCustomImpl;
import kr.muve.common.repository.order.SpringDataOrderRepository;
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

    @Override
    public OrderDetailRes getOrderDetail(Long orderId) {
        return OrderDetailRes.from(orderRepository.findById(orderId)
                .orElseThrow(() -> new BaseException(ErrorCode.ORDER_NOT_FOUND)));
    }
}
