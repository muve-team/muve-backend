package kr.muve.api.category.service;

import kr.muve.common.repository.category.SpringDataCategoryRepository;
import kr.muve.common.service.category.AllCategory;
import kr.muve.common.service.category.CategoryAllRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryQueryService implements AllCategory {

    private final SpringDataCategoryRepository categoryRepository;

    @Override
    public List<CategoryAllRes> getCategoryList() {
        return CategoryAllRes.from(categoryRepository.findAll());
    }
}
