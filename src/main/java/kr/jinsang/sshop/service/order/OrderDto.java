package kr.jinsang.sshop.service.order;

import kr.jinsang.sshop.controller.OrderForm;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDto {

    private Long userId;
    private Long productId;
    private Integer count;

    private OrderDto() {
    }

    public static OrderDto from(OrderForm orderForm) {
        return new OrderDto(orderForm.getUserId(), orderForm.getProductId(), orderForm.getCount());
    }

}
