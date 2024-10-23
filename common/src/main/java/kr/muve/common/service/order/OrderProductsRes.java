package kr.muve.common.service.order;

import kr.muve.common.domain.orderproduct.OrderProductJpaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderProductsRes {

    private Long orderProductId;
    private String name;
    private String imageUrl;
    private Long price;
    private Integer count;

    public static OrderProductsRes from(OrderProductJpaEntity orderProduct) {
        return new OrderProductsRes(orderProduct.getId(), orderProduct.getProductJpaEntity().getName(),
                orderProduct.getProductJpaEntity().getImageUrl(), orderProduct.getPrice(), orderProduct.getCount());
    }
}
