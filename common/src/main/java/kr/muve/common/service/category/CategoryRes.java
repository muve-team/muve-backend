package kr.muve.common.service.category;

import kr.muve.common.domain.category.CategoryJpaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class CategoryRes {

    private Long categoryId;
    private String name;
    private String slug;
    private String imageUrl;
    private List<CategoryRes> children;

    public static List<CategoryRes> from(List<CategoryJpaEntity> categories) {
        return categories.stream()
                .map(CategoryRes::fromEntity)
                .toList();
    }

    private static CategoryRes fromEntity(CategoryJpaEntity category) {
        // 재귀적으로 하위 카테고리를 CategoryRes로 변환
        List<CategoryRes> childCategories = category.getChild().stream()
                .map(CategoryRes::fromEntity)
                .toList();

        return new CategoryRes(
                category.getId(),
                category.getName(),
                category.getSlug(),
                category.getImageUrl(),
                childCategories
        );
    }
}
