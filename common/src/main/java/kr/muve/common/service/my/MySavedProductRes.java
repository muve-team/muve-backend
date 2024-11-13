package kr.muve.common.service.my;

import kr.muve.common.domain.saved.product.SavedProductJpaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MySavedProductRes {

    private Long mySavedProductId;
    private String name;
    private String imageUrl;

    public static List<MySavedProductRes> from(List<SavedProductJpaEntity> savedProductJpaEntities) {
        return savedProductJpaEntities.stream()
                .map(sp-> new MySavedProductRes(
                        sp.getId(),
                        sp.getProductJpaEntity().getName(),
                        sp.getProductJpaEntity().getImageUrl()
                ))
                .toList();
    }
}
