package kr.muve.common.service.category;

import kr.muve.common.domain.category.CategoryJpaEntity;
import kr.muve.common.domain.category.CategoryJpaEntity;

import java.util.List;

public interface FindCategories {

    CategoryJpaEntity findById(Long id);

    List<CategoryJpaEntity> findCategories();
}
