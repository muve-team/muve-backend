package kr.muve.admin.service;

import kr.muve.common.controller.CategoryForm;
import kr.muve.common.domain.category.CategoryJpaEntity;
import kr.muve.common.exception.CategoryNotFoundException;
import kr.muve.common.repository.category.SpringDataCategoryRepository;
import kr.muve.common.service.category.CreateCategory;
import kr.muve.common.service.category.FindCategories;
import kr.muve.common.service.category.UpdateCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService implements CreateCategory, FindCategories, UpdateCategory {

    private final SpringDataCategoryRepository categoryRepository;

    // 카테고리 등록
    @Override
    @Transactional
    public void create(String name, String slug, String imageUrl) {
        CategoryJpaEntity categoryJpaEntity = CategoryJpaEntity.createCategory(name,slug,imageUrl);
        categoryRepository.save(categoryJpaEntity);
    }

    // 카테고리 조회
    @Override
    public List<CategoryJpaEntity> findCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public CategoryJpaEntity findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("카테고리를 찾을 수 없습니다."));
    }

    // 카테고리 수정
    @Override
    @Transactional
    public void update(CategoryForm form) {
        CategoryJpaEntity categoryJpaEntity = findById(form.getId());
        categoryJpaEntity.update(form.getName(), form.getSlug());
    }

}