package kr.muve.common.service.order;

import kr.muve.common.domain.order.OrderJpaEntity;
import kr.muve.common.domain.order.OrderStatus;
import kr.muve.common.domain.user.Address;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderDetailRes {

    private Long orderId;
    // private Long orderNumber; todo : orderJpaEntity에 추가해야함
    private OrderStatus status;
    private LocalDateTime orderDate;
    private List<OrderProductsRes> orderProductsRes;

//    // 총 상품금액, 배송비, 총 주문 금액
//    private Long totalProductPrice;
//    private Long deliveryPrice;
//    private Long totalOrderPrice;
//
//    // 보내는 사람
//    private SendUserRes sendUserRes;
//    // 정보 : 이름, 전화번호, 반송주소, 배송메모
//
//    // 받는 사람
//    private ReceiveUser receiveUser;
//    // 정보 : 이름, 전화번호, 반송주소


    public static OrderDetailRes from(OrderJpaEntity order) {
        return new OrderDetailRes(order.getId(), order.getStatus(), order.getOrderDate(),
                order.getOrderProductJpaEntities().stream()
                        .map(orderProductJpaEntity -> OrderProductsRes.from(orderProductJpaEntity))
                        .toList()
        );
    }
}
