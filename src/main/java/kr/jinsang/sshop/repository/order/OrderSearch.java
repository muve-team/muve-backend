package kr.jinsang.sshop.repository.order;

import kr.jinsang.sshop.domain.order.OrderStatus;
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
