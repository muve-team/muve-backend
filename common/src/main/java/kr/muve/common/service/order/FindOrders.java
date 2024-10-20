package kr.muve.common.service.order;

import kr.muve.common.domain.order.OrderJpaEntity;
import kr.muve.common.repository.order.OrderSearch;

import java.util.List;

public interface FindOrders {
    List<OrderJpaEntity> findOrders(OrderSearch orderSearch);
}
