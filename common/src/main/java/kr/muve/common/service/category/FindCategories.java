package kr.muve.common.service.category;

import kr.muve.common.domain.category.Category;

import java.util.List;

public interface FindCategories {

    Category findOne(Long id);

    List<Category> findCategories();
}
