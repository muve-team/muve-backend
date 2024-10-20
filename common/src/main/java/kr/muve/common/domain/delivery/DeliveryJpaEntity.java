package kr.muve.common.domain.delivery;

import jakarta.persistence.*;
import kr.muve.common.domain.user.Address;
import java.time.LocalDateTime;

@Entity
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



}
