package kr.muve.common.domain.order;

import jakarta.persistence.*;
import kr.muve.common.domain.orderproduct.OrderProduct;
import kr.muve.common.domain.user.User;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    private LocalDateTime orderDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    protected Order() {

    }

    private Order(User user, OrderProduct... orderProducts) {
        Arrays.stream(orderProducts).forEach(this::addOrderProduct);
        this.user = user;
        this.status = OrderStatus.ORDER;
        this.orderDate = LocalDateTime.now();
    }

    public static Order createOrder(User user, OrderProduct... orderProducts) {
        return new Order(user, orderProducts);
    }

    public void addOrderProduct(OrderProduct orderProduct) {
        orderProducts.add(orderProduct);
        orderProduct.updateOrder(this);
    }

    public Long getTotalPrice() {
        return orderProducts.stream().mapToLong(o -> o.getPrice() * o.getCount()).sum();
    }

    public void cancelOrder() {
        this.status = OrderStatus.CANCEL;
    }
}
