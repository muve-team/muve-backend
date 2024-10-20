package kr.muve.admin.service.category;

import jakarta.persistence.EntityManager;
import kr.muve.admin.service.CategoryService;
import kr.muve.common.controller.CategoryForm;
import kr.muve.common.domain.category.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CategoryServiceTest {

    @Autowired
    CategoryService categoryService;

    @Autowired
    EntityManager em;

    @Test
    void 카테고리_등록() {
        // given
        String koreanName = "전자기기";
        String englishName = "electric";

        // when
        categoryService.create(koreanName, englishName);
        Category foundCategory = em.find(Category.class, 1L);

        //then
        assertThat(foundCategory.getKoreanName()).isEqualTo(koreanName);
        assertThat(foundCategory.getEnglishName()).isEqualTo(englishName);

    }

    @Test
    void 카테고리_수정() {
        //given
        categoryService.create("책", "book");
        CategoryForm form = new CategoryForm(1L, "신발", "shoes");

        //when
        categoryService.update(form);
        Category foundCategory = em.find(Category.class, 1L);

        //then
        assertThat(foundCategory.getKoreanName()).isEqualTo(form.getKoreanName());
        assertThat(foundCategory.getEnglishName()).isEqualTo(form.getEnglishName());
    }
}