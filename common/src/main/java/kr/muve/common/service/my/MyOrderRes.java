package kr.muve.common.service.my;

import kr.muve.common.domain.order.OrderJpaEntity;
import kr.muve.common.domain.order.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class MyOrderRes {

    private Long orderId;
    private OrderStatus status;
    private LocalDateTime orderDate;
    private List<MyOrderProductRes> orderProducts;

    public static List<MyOrderRes> from(List<OrderJpaEntity> orders) {
        return orders.stream()
                .map(o -> new MyOrderRes(o.getId(), o.getStatus(), o.getOrderDate(),
                        MyOrderProductRes.from(o.getOrderProductJpaEntities())))
                .toList();

    }
}
