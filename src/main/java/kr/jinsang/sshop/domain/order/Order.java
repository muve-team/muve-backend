package kr.jinsang.sshop.domain.order;

import jakarta.persistence.*;
import kr.jinsang.sshop.domain.user.User;

import java.lang.reflect.Member;
import java.time.LocalDateTime;

@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    private LocalDateTime orderDate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    protected Order() {

    }

    public Order(OrderStatus status, LocalDateTime orderDate, User user) {
        this.status = status;
        this.orderDate = orderDate;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status=" + status +
                ", orderDate=" + orderDate +
                ", user=" + user +
                '}';
    }
}

