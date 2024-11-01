package kr.muve.common.domain.saved;

import jakarta.persistence.*;
import kr.muve.common.domain.savedProduct.SavedProductJpaEntity;
import kr.muve.common.domain.user.UserJpaEntity;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Table(name = "saved")
public class SavedJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "saved_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserJpaEntity userJpaEntity;

    @OneToMany(mappedBy = "savedJpaEntity", cascade = CascadeType.PERSIST)
    private List<SavedProductJpaEntity> savedProductJpaEntities = new ArrayList<>();

    protected SavedJpaEntity() {}

    private SavedJpaEntity(UserJpaEntity userJpaEntity) {
        this.userJpaEntity = userJpaEntity;
    }


    public static SavedJpaEntity createSaved(UserJpaEntity userJpaEntity) {
        SavedJpaEntity savedJpaEntity = new SavedJpaEntity(userJpaEntity);
        return savedJpaEntity;
    }

    public void addSavedProduct(SavedProductJpaEntity savedProductJpaEntity) {
        this.savedProductJpaEntities.add(savedProductJpaEntity);
        savedProductJpaEntity.updateSaved(this); // 역방향 관계도 설정
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SavedJpaEntity that = (SavedJpaEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
