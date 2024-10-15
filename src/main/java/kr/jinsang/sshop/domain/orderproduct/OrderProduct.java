package kr.jinsang.sshop.domain.orderproduct;

import jakarta.persistence.*;
import kr.jinsang.sshop.domain.order.Order;
import kr.jinsang.sshop.domain.product.Product;
import lombok.Getter;

@Entity
@Getter
public class OrderProduct {

    @Id
    @GeneratedValue
    @Column(name = "order_product_id")
    private Long id;

    private Long price;

    private Integer count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;


}
