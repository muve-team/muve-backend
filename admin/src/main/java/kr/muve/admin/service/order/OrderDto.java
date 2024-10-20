package kr.muve.admin.service.order;

import kr.muve.common.controller.OrderForm;
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
