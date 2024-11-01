package kr.muve.common.domain.timedeal;

import jakarta.persistence.*;
import kr.muve.common.domain.product.ProductJpaEntity;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "time_deal")
public class TimeDealJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "time_deal_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "product_id")
    private ProductJpaEntity productJpaEntity;

    @Column(name = "start_at")
    private LocalDateTime startAt;

    @Column(name = "end_at")
    private LocalDateTime endAt;
}
