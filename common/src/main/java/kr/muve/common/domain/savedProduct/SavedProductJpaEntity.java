package kr.muve.common.domain.savedProduct;

import jakarta.persistence.*;
import kr.muve.common.domain.order.OrderJpaEntity;
import kr.muve.common.domain.orderproduct.OrderProductJpaEntity;
import kr.muve.common.domain.product.ProductJpaEntity;
import kr.muve.common.domain.saved.SavedJpaEntity;
import kr.muve.common.domain.user.UserJpaEntity;
import lombok.Getter;

import java.util.Objects;

@Entity
@Getter
public class SavedProductJpaEntity {

    @Id
    @GeneratedValue
    @Column(name = "saved_product_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "saved_id")
    private SavedJpaEntity savedJpaEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductJpaEntity productJpaEntity;

    protected SavedProductJpaEntity() {}

    private SavedProductJpaEntity(ProductJpaEntity productJpaEntity) {
        this.productJpaEntity = productJpaEntity;
    }

    public static SavedProductJpaEntity createSavedProduct(ProductJpaEntity productJpaEntity) {
        return new SavedProductJpaEntity(productJpaEntity);
    }

    public void updateSaved(SavedJpaEntity savedJpaEntity) {
        this.savedJpaEntity = savedJpaEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SavedProductJpaEntity that = (SavedProductJpaEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
