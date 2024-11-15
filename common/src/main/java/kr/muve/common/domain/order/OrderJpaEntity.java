package kr.muve.common.domain.order;

import jakarta.persistence.*;
import kr.muve.common.domain.delivery.DeliveryJpaEntity;
import kr.muve.common.domain.order.product.OrderProductJpaEntity;
import kr.muve.common.domain.user.UserJpaEntity;
import kr.muve.common.service.order.OrderCreateCommand;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Table(name = "orders")
public class OrderJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    private LocalDateTime orderDate;

    private String ordererName;
    private String ordererPhoneNumber;
    private String ordererEmail;

    private String receiverName;
    private String receiverPhoneNumber;

    private String paymentMethod;

    @OneToMany(mappedBy = "orderJpaEntity", cascade = CascadeType.ALL)
    private List<OrderProductJpaEntity> orderProductJpaEntities = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserJpaEntity userJpaEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private DeliveryJpaEntity deliveryJpaEntity;

    protected OrderJpaEntity() {

    }

    private OrderJpaEntity(OrderCreateCommand command, DeliveryJpaEntity deliveryJpaEntity,
                           UserJpaEntity userJpaEntity, OrderProductJpaEntity... orderProductJpaEntities) {
        Arrays.stream(orderProductJpaEntities).forEach(this::addOrderProduct);

        this.userJpaEntity = userJpaEntity;
        this.ordererName = command.ordererName();
        this.ordererPhoneNumber = command.ordererPhoneNumber();
        this.ordererEmail = command.ordererEmail();
        this.deliveryJpaEntity = deliveryJpaEntity;
        this.receiverName = command.receiverName();
        this.receiverPhoneNumber = command.receiverPhoneNumber();
        deliveryJpaEntity.updateOrder(this);
        this.status = OrderStatus.ORDER;
        this.orderDate = LocalDateTime.now();
        this.paymentMethod = "card";
    }

    public static OrderJpaEntity createOrder(OrderCreateCommand command, DeliveryJpaEntity deliveryJpaEntity,
                                             UserJpaEntity userJpaEntity, OrderProductJpaEntity... orderProductJpaEntities) {
        return new OrderJpaEntity(command, deliveryJpaEntity, userJpaEntity, orderProductJpaEntities);
    }

    public void addOrderProduct(OrderProductJpaEntity orderProductJpaEntity) {
        orderProductJpaEntities.add(orderProductJpaEntity);
        orderProductJpaEntity.updateOrder(this);
    }

    public Long getTotalPrice() {
        return orderProductJpaEntities.stream().mapToLong(o -> o.getPrice() * o.getCount()).sum();
    }

    public void cancelOrder() {
        this.status = OrderStatus.CANCEL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderJpaEntity that = (OrderJpaEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
