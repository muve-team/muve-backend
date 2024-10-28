package kr.muve.common.domain.category;

import jakarta.persistence.*;
import kr.muve.common.domain.product.ProductJpaEntity;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Table(name = "category")
public class CategoryJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    private String name;

    private String slug;

    private String imageUrl;

    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "categoryJpaEntity")
    private List<ProductJpaEntity> productJpaEntities = new ArrayList<>();

    protected CategoryJpaEntity() {}

    private CategoryJpaEntity(String name, String slug, String imageUrl) {
        this.name = name;
        this.slug = slug;
        this.imageUrl = imageUrl;
    }

    public static CategoryJpaEntity createCategory(String name, String slug, String imageUrl) {
        return new CategoryJpaEntity(name,slug,imageUrl);
    }

    public void update(String name, String slug, String imageUrl) {
        this.name = name;
        this.slug = slug;
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryJpaEntity that = (CategoryJpaEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}