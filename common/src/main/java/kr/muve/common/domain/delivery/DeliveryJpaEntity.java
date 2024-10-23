package kr.muve.common.domain.delivery;

import jakarta.persistence.*;
import kr.muve.common.domain.user.Address;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
public class DeliveryJpaEntity {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

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
