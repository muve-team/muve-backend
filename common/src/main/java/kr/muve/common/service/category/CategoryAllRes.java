package kr.muve.common.service.category;

import kr.muve.common.domain.category.CategoryJpaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CategoryAllRes {

    private Long categoryId;
    private String name;
    private String slug;
    private String imageUrl;


    public static List<CategoryAllRes> from(List<CategoryJpaEntity> categories) {
        return categories.stream()
                .map(c -> new CategoryAllRes(c.getId(), c.getName(), c.getSlug(), c.getImageUrl()))
                .toList();
    }

}
