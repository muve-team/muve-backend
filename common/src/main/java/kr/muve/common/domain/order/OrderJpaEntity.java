package kr.muve.common.domain.order;

import jakarta.persistence.*;
import kr.muve.common.domain.orderproduct.OrderProductJpaEntity;
import kr.muve.common.domain.user.UserJpaEntity;
import kr.muve.common.domain.user.UserJpaEntity;
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
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    private LocalDateTime orderDate;

    @OneToMany(mappedBy = "orderJpaEntity", cascade = CascadeType.ALL)
    private List<OrderProductJpaEntity> orderProductJpaEntities = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserJpaEntity userJpaEntity;

    protected OrderJpaEntity() {

    }

    private OrderJpaEntity(UserJpaEntity userJpaEntity, OrderProductJpaEntity... orderProductJpaEntities) {
        Arrays.stream(orderProductJpaEntities).forEach(this::addOrderProduct);
        this.userJpaEntity = userJpaEntity;
        this.status = OrderStatus.ORDER;
        this.orderDate = LocalDateTime.now();
    }

    public static OrderJpaEntity createOrder(UserJpaEntity userJpaEntity, OrderProductJpaEntity... orderProductJpaEntities) {
        return new OrderJpaEntity(userJpaEntity, orderProductJpaEntities);
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
