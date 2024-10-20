package kr.muve.common.domain.product;

import jakarta.persistence.*;
import kr.muve.common.domain.category.CategoryJpaEntity;
import kr.muve.common.exception.NotEnoughStockException;
import kr.muve.common.service.product.ProductDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class ProductJpaEntity {

    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private Long id;

    private String name;

    private Long price;

    private Integer stockQuantity;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryJpaEntity categoryJpaEntity;

    protected ProductJpaEntity() {
    }

    private ProductJpaEntity(String name, Long price, Integer stockQuantity, CategoryJpaEntity categoryJpaEntity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.categoryJpaEntity = categoryJpaEntity;
    }

    public static ProductJpaEntity createProduct(String name, Long price, Integer stockQuantity, CategoryJpaEntity categoryJpaEntity) {
        return new ProductJpaEntity(name, price, stockQuantity, categoryJpaEntity);
    }

    public void update(ProductDto dto, CategoryJpaEntity categoryJpaEntity) {
        this.name = dto.getName();
        this.price = dto.getPrice();
        this.stockQuantity = dto.getStockQuantity();
        this.categoryJpaEntity = categoryJpaEntity;
    }

    public void removeStock(Integer count) {
        Integer restStock = this.stockQuantity - count;
        if (restStock < 0) {
            throw new NotEnoughStockException("재고가 부족합니다");
        }
        this.stockQuantity = restStock;
    }

    public void addStock(Integer count) {
        this.stockQuantity += count;
    }
}