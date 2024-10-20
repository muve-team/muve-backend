package kr.muve.admin.service;

import kr.muve.common.controller.CategoryForm;
import kr.muve.common.domain.category.Category;
import kr.muve.common.repository.category.CategoryRepository;
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

    private final CategoryRepository categoryRepository;

    // 카테고리 등록
    @Override
    @Transactional
    public void create(String koreanName, String englishName) {
        Category category = Category.createCategory(koreanName,englishName);
        categoryRepository.save(category);
    }

    // 카테고리 조회
    @Override
    public List<Category> findCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findOne(Long id) {
        return categoryRepository.findOne(id);
    }

    // 카테고리 수정
    @Override
    @Transactional
    public void update(CategoryForm form) {
        Category category = categoryRepository.findOne(form.getId());
        category.update(form.getKoreanName(), form.getEnglishName());
    }

}