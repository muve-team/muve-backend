package kr.muve.common.domain.product;

import jakarta.persistence.*;
import kr.muve.common.domain.category.CategoryJpaEntity;
import kr.muve.common.domain.saved.SavedJpaEntity;
import kr.muve.common.domain.savedProduct.SavedProductJpaEntity;
import kr.muve.common.exception.NotEnoughStockException;
import kr.muve.common.service.product.ProductDto;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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

    private String imageUrl;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryJpaEntity categoryJpaEntity;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "saved_id")
    private List<SavedProductJpaEntity> savedProductJpaEntities;

    protected ProductJpaEntity() {}

    private ProductJpaEntity(String name, Long price, Integer stockQuantity, String imageUrl, CategoryJpaEntity categoryJpaEntity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.imageUrl = imageUrl;
        this.categoryJpaEntity = categoryJpaEntity;
    }

    public static ProductJpaEntity createProduct(String name, Long price, Integer stockQuantity, String imageUrl, CategoryJpaEntity categoryJpaEntity) {
        return new ProductJpaEntity(name, price, stockQuantity, imageUrl, categoryJpaEntity);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductJpaEntity that = (ProductJpaEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}