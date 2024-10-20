package kr.muve.common.repository.order;

import kr.muve.common.domain.order.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderSearch {
    private String userName;
    private OrderStatus orderStatus;
}
