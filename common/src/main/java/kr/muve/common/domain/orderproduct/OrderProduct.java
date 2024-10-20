package kr.muve.common.domain.orderproduct;

import jakarta.persistence.*;
import kr.muve.common.domain.order.Order;
import kr.muve.common.domain.product.Product;
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

    private OrderProduct(Product product, Long price, Integer count) {
        this.product = product;
        this.price = price;
        this.count = count;
    }

    protected OrderProduct() {

    }

    public static OrderProduct createOrderProduct(Product product, Integer count) {
        OrderProduct orderProduct = new OrderProduct(product, product.getPrice(), count);
        product.removeStock(count);
        return orderProduct;
    }

    public void updateOrder(Order order) {
        this.order = order;
    }

}
