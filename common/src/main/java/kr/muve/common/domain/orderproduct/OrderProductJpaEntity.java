package kr.muve.common.domain.orderproduct;

import jakarta.persistence.*;
import kr.muve.common.domain.order.OrderJpaEntity;
import kr.muve.common.domain.product.ProductJpaEntity;
import lombok.Getter;

import java.util.Objects;

@Entity
@Getter
@Table(name = "order_product")
public class OrderProductJpaEntity {

    @Id
    @GeneratedValue
    @Column(name = "order_product_id")
    private Long id;

    private Long price;

    private Integer count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderJpaEntity orderJpaEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductJpaEntity productJpaEntity;

    private OrderProductJpaEntity(ProductJpaEntity productJpaEntity, Long price, Integer count) {
        this.productJpaEntity = productJpaEntity;
        this.price = price;
        this.count = count;
    }

    protected OrderProductJpaEntity() {

    }

    public static OrderProductJpaEntity createOrderProduct(ProductJpaEntity productJpaEntity, Integer count) {
        OrderProductJpaEntity orderProductJpaEntity = new OrderProductJpaEntity(productJpaEntity, productJpaEntity.getPrice(), count);
        productJpaEntity.removeStock(count);
        return orderProductJpaEntity;
    }

    public void updateOrder(OrderJpaEntity orderJpaEntity) {
        this.orderJpaEntity = orderJpaEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderProductJpaEntity that = (OrderProductJpaEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
