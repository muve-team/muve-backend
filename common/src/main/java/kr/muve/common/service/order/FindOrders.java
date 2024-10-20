package kr.muve.common.service.order;

import kr.muve.common.domain.order.Order;
import kr.muve.common.repository.order.OrderSearch;

import java.util.List;

public interface FindOrders {
    List<Order> findOrders(OrderSearch orderSearch);
}
