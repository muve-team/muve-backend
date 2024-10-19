package kr.jinsang.sshop.service.category;

import jakarta.persistence.EntityManager;
import kr.jinsang.sshop.controller.CategoryForm;
import kr.jinsang.sshop.domain.category.Category;
import kr.jinsang.sshop.repository.category.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CategoryServiceTest {

    @Autowired
    CategoryService categoryService;

    @Autowired
    EntityManager em;

    @Test
    void 등록() {
        // given
        String categoryName = "전자기기";

        // when
        categoryService.create(categoryName);
        Category foundCategory = em.find(Category.class, 1L);

        //then
        assertThat(foundCategory.getName()).isEqualTo(categoryName);

    }

    @Test
    void update() {
        //given
        categoryService.create("책");
        CategoryForm form = new CategoryForm(1L, "신발");

        //when
        categoryService.update(form);
        Category foundCategory = em.find(Category.class, 1L);

        //then
        assertThat(foundCategory.getName()).isEqualTo(form.getName());
    }
}