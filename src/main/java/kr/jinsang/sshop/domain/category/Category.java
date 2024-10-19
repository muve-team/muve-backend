package kr.jinsang.sshop.domain.category;

import jakarta.persistence.*;
import kr.jinsang.sshop.domain.product.Product;
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

    private String name;

    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();

    protected Category() {}

    private Category(String name) {
        this.name = name;
    }

    public static Category createCategory(String name) {
        return new Category(name);
    }

    public void update(String name) {
        this.name = name;
    }

}
