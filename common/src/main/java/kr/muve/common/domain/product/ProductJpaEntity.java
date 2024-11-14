package kr.muve.common.domain.product;

import jakarta.persistence.*;
import kr.muve.common.domain.cart.CartJpaEntity;
import kr.muve.common.domain.category.CategoryJpaEntity;
import kr.muve.common.domain.saved.product.SavedProductJpaEntity;
import kr.muve.common.domain.timedeal.TimeDealJpaEntity;
import kr.muve.common.exception.NotEnoughStockException;
import kr.muve.common.service.product.ProductDto;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Table(name = "product")
@EntityListeners(AuditingEntityListener.class)
public class ProductJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    private String koreanName;
    private String englishName;

    private Long price;

    private String brandKoreanName;
    private String brandEnglishName;

    private Integer stockQuantity;

    private String imageUrl;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryJpaEntity categoryJpaEntity;

    @OneToMany(mappedBy = "productJpaEntity", cascade = CascadeType.PERSIST)
    private List<SavedProductJpaEntity> savedProductJpaEntities = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "cart_id")
    private CartJpaEntity cartJpaEntity;

    @OneToOne(mappedBy = "productJpaEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private TimeDealJpaEntity timeDealJpaEntity;

    public ProductJpaEntity() {}

    private ProductJpaEntity(String koreanName, String englishName, String brandKoreanName, String brandEnglishName,
                             Long price, Integer stockQuantity, String imageUrl, CategoryJpaEntity categoryJpaEntity) {
        this.koreanName = koreanName;
        this.englishName = englishName;
        this.brandKoreanName = brandKoreanName;
        this.brandEnglishName = brandEnglishName;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.imageUrl = imageUrl;
        this.categoryJpaEntity = categoryJpaEntity;
    }

    public static ProductJpaEntity createProduct(String koreanName, String englishName, String brandKoreanName,
                                                 String brandEnglishName, Long price, Integer stockQuantity, String imageUrl, CategoryJpaEntity categoryJpaEntity) {
        return new ProductJpaEntity(koreanName, englishName, brandKoreanName, brandEnglishName, price, stockQuantity, imageUrl, categoryJpaEntity);
    }

    public void update(ProductDto dto, CategoryJpaEntity categoryJpaEntity, String imageUrl) {
        this.koreanName = dto.getKoreanName();
        this.englishName = dto.getEnglishName();
        this.brandKoreanName = dto.getBrandKoreanName();
        this.brandEnglishName = dto.getBrandEnglishName();
        this.price = dto.getPrice();
        this.stockQuantity = dto.getStockQuantity();
        this.categoryJpaEntity = categoryJpaEntity;

        if (!ObjectUtils.isEmpty(imageUrl)) {
            this.imageUrl = imageUrl;
        }
    }

    public void removeStock(Integer count) {
        this.stockQuantity = checkPreCondition(count);
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

    public int checkPreCondition(Integer count) {
        int restStock = this.stockQuantity - count;
        if (restStock < 0) {
            throw new NotEnoughStockException("재고가 부족합니다");
        }

        return restStock;
    }
}