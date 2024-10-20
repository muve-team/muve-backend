package kr.jinsang.sshop.service.category;

import kr.jinsang.sshop.controller.CategoryForm;
import kr.jinsang.sshop.domain.category.Category;
import kr.jinsang.sshop.repository.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;

    // 카테고리 등록
    @Transactional
    public void create(String name) {
        Category category = Category.createCategory(name);
        categoryRepository.save(category);
    }

    // 카테고리 조회
    public List<Category> findCategories() {
        return categoryRepository.findAll();
    }

    public Category findOne(Long id) {
        return categoryRepository.findOne(id);
    }

    // 카테고리 수정
    @Transactional
    public void update(CategoryForm form) {
        Category category = categoryRepository.findOne(form.getId());
        category.update(form.getName());
    }

}