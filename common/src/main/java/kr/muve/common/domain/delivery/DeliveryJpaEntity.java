package kr.muve.common.domain.delivery;

import jakarta.persistence.*;
import kr.muve.common.domain.order.OrderJpaEntity;
import kr.muve.common.domain.user.Address;
import kr.muve.common.domain.user.UserJpaEntity;
import kr.muve.common.service.order.OrderCreateCommand;
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

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "deliveryJpaEntity")
    private OrderJpaEntity orderJpaEntity;

    private String company;

    private String trackingNumber;

    @Enumerated(value = EnumType.STRING)
    private DeliveryStatus status;

    private LocalDateTime deliveryDate;

    private String deliveryRequest;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "city")),
            @AttributeOverride(name = "street", column = @Column(name = "street")),
            @AttributeOverride(name = "zipcode", column = @Column(name = "zipcode"))
    })
    private Address address;


    public DeliveryJpaEntity(Address address) {
        this.address = address;
        this.company = "대한통운";
        this.trackingNumber = "123456789";
        this.status = DeliveryStatus.READY;
        this.deliveryDate = LocalDateTime.now();
        this.deliveryRequest = "DOOR";
    }

    protected DeliveryJpaEntity() {

    }

    public static DeliveryJpaEntity createDelivery(OrderCreateCommand command) {
        Address address = new Address(command.city(), command.street(), command.zipcode());
        return new DeliveryJpaEntity(address);
    }

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

    public void updateOrder(OrderJpaEntity orderJpaEntity) {
        this.orderJpaEntity = orderJpaEntity;
    }
}
