package kr.muve.common.domain.category;

import jakarta.persistence.*;
import kr.muve.common.domain.product.Product;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String koreanName;

    private String englishName;

    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();

    protected Category() {}

    private Category(String koreanName, String englishName) {
        this.koreanName = koreanName;
        this.englishName = englishName;
    }

    public static Category createCategory(String koreanName, String englishName) {
        return new Category(koreanName,englishName);
    }

    public void update(String koreanName, String englishName) {
        this.koreanName = koreanName;
        this.englishName = englishName;
    }
}