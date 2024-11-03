package kr.muve.common.domain.delivery;

import jakarta.persistence.*;
import kr.muve.common.domain.order.OrderJpaEntity;
import kr.muve.common.domain.user.Address;
import kr.muve.common.domain.user.UserJpaEntity;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Table(name = "delivery")
public class DeliveryJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderJpaEntity orderJpaEntity;

    @Embedded
    private Address address;

    private String company;

    private String trackingNumber;

    @Enumerated(value = EnumType.STRING)
    private DeliveryStatus status;

    private LocalDateTime deliveryDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryJpaEntity that = (DeliveryJpaEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
