package kr.muve.common.domain.category;

import jakarta.persistence.*;
import kr.muve.common.domain.product.ProductJpaEntity;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class CategoryJpaEntity {

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String koreanName;

    private String englishName;

    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "categoryJpaEntity")
    private List<ProductJpaEntity> productJpaEntities = new ArrayList<>();

    protected CategoryJpaEntity() {}

    private CategoryJpaEntity(String koreanName, String englishName) {
        this.koreanName = koreanName;
        this.englishName = englishName;
    }

    public static CategoryJpaEntity createCategory(String koreanName, String englishName) {
        return new CategoryJpaEntity(koreanName,englishName);
    }

    public void update(String koreanName, String englishName) {
        this.koreanName = koreanName;
        this.englishName = englishName;
    }
}