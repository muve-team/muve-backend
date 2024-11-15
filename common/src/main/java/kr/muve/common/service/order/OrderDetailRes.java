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
    private OrderStatus status;
    private LocalDateTime orderDate;

    // 주문자 정보
    private String ordererName;
    private String ordererPhoneNumber;
    private String ordererEmail;

    // 배송 정보
    private String receiverName;
    private String receiverPhoneNumber;
    private Address address;
    private String deliveryRequest;
    private String deliveryCompany;
    private String trackingNumber;
    private String deliveryStatus;
    private LocalDateTime deliveryDate;

    // 결제 정보
    private String paymentMethod;
    private Long totalAmount;

    // 주문 상품 정보
    private List<OrderProductsRes> orderProducts;

    //    // 보내는 사람
    //    private SendUserRes sendUserRes;
    //    // 정보 : 이름, 전화번호, 반송주소, 배송메모
    //
    //    // 받는 사람
    //    private ReceiveUser receiveUser;
    //    // 정보 : 이름, 전화번호, 반송주소


    public static OrderDetailRes from(OrderJpaEntity order) {
        return new OrderDetailRes(
                order.getId(),
                order.getStatus(),
                order.getOrderDate(),
                order.getOrdererName(),
                order.getOrdererPhoneNumber(),
                order.getOrdererEmail(),
                order.getReceiverName(),
                order.getReceiverPhoneNumber(),
                order.getDeliveryJpaEntity().getAddress(),
                order.getDeliveryJpaEntity().getDeliveryRequest(),
                order.getDeliveryJpaEntity().getCompany(),
                order.getDeliveryJpaEntity().getTrackingNumber(),
                order.getDeliveryJpaEntity().getStatus().toString(),
                order.getDeliveryJpaEntity().getDeliveryDate(),
                order.getPaymentMethod(),
                order.getTotalPrice(),
                order.getOrderProductJpaEntities().stream()
                        .map(OrderProductsRes::from)
                        .toList()
        );
    }
}
