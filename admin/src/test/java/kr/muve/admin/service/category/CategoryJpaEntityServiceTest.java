package kr.muve.admin.service.category;

import jakarta.persistence.EntityManager;
import kr.muve.AdminApplication;
import kr.muve.admin.service.CategoryService;
import kr.muve.common.controller.CategoryForm;
import kr.muve.common.domain.category.CategoryJpaEntity;
import kr.muve.common.domain.category.CategoryJpaEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CategoryJpaEntityServiceTest {

    @Autowired
    CategoryService categoryService;

    @Autowired
    EntityManager em;

    @Test
    void 카테고리_등록() {
        // given
        String name = "전자기기";
        String slug = "electric";
        String imageUrl = "1.jpg";

        // when
        categoryService.create(name, slug, imageUrl);
        CategoryJpaEntity foundCategoryJpaEntity = em.find(CategoryJpaEntity.class, 1L);

        //then
        assertThat(foundCategoryJpaEntity.getName()).isEqualTo(name);
        assertThat(foundCategoryJpaEntity.getSlug()).isEqualTo(slug);
        assertThat(foundCategoryJpaEntity.getImageUrl()).isEqualTo(imageUrl);

    }

    @Test
    void 카테고리_수정() {
        //given
        categoryService.create("책", "book", "1.jpg");
        CategoryForm form = new CategoryForm(1L, "신발", "shoes", "2.jpg");

        //when
        categoryService.update(form);
        CategoryJpaEntity foundCategoryJpaEntity = em.find(CategoryJpaEntity.class, 1L);

        //then
        assertThat(foundCategoryJpaEntity.getName()).isEqualTo(form.getName());
        assertThat(foundCategoryJpaEntity.getSlug()).isEqualTo(form.getSlug());
    }
}