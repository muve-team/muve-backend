package kr.muve.common.repository.order;

import kr.muve.common.domain.order.OrderJpaEntity;

import java.util.List;

public interface OrderRepositoryCustom {
    List<OrderJpaEntity> findDynamicOrders(OrderSearch orderSearch);
}
