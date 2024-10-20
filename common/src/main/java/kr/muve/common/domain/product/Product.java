package kr.muve.common.domain.product;

import jakarta.persistence.*;
import kr.muve.common.exception.NotEnoughStockException;
import kr.muve.common.service.product.ProductDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private Long id;

    private String name;

    private Long price;

    private Integer stockQuantity;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    protected Product() {}

    private Product(String name, Long price, Integer stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public static Product createProduct(String name, Long price, Integer stockQuantity) {
        return new Product(name, price, stockQuantity);
    }

    public void update(ProductDto dto) {
        this.name = dto.getName();
        this.price = dto.getPrice();
        this.stockQuantity = dto.getStockQuantity();
    }

    public void removeStock(Integer count) {
        Integer restStock = this.stockQuantity - count;
        if (restStock < 0) {
            throw new NotEnoughStockException("재고가 부족합니다");
        }
        this.stockQuantity = restStock;
    }


    public Long getId() {
        return id;
    }
}
