package kr.muve.common.service.my;

import kr.muve.common.domain.order.product.OrderProductJpaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MyOrderProductRes {

    private Long orderProductId;
    private Long price;
    private Integer count;

    public static List<MyOrderProductRes> from(List<OrderProductJpaEntity> orderProducts) {
        return orderProducts.stream()
                .map(op -> new MyOrderProductRes(op.getId(), op.getPrice(), op.getCount()))
                .toList();
    }

}
