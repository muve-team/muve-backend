package kr.muve.admin.controller;

import jakarta.validation.Valid;
import kr.muve.common.controller.CategoryForm;
import kr.muve.common.controller.ImageFileValidator;
import kr.muve.common.domain.category.CategoryJpaEntity;
import kr.muve.common.service.category.CreateCategory;
import kr.muve.common.service.category.FindCategories;
import kr.muve.common.service.category.UpdateCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final CreateCategory createCategory;
    private final FindCategories findCategories;
    private final UpdateCategory updateCategory;
    private final ImageFileValidator imageFileValidator;


    @GetMapping(value = "/category/new")
    public String createCategoryForm(Model model) {
        model.addAttribute("categoryForm", new CategoryForm());
        return "categories/createCategoryForm";
    }

    @PostMapping(value = "/category/new")
    public String create(@Valid CategoryForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "categories/createCategoryForm";
        }
        createCategory.create(form.getName(), form.getSlug(), form.getImageUrl());
        return "redirect:/";
    }

    @GetMapping(value = "/categories")
    public String getCategories(Model model) {
        model.addAttribute("categories", findCategories.findCategories());
        return "categories/categoryList";
    }

    @GetMapping(value = "/category/{categoryId}/edit")
    public String updateCategoryForm(@PathVariable("categoryId") Long categoryId, Model model) {
        CategoryJpaEntity categoryJpaEntity = findCategories.findById(categoryId);
        CategoryForm categoryForm = CategoryForm.from(categoryJpaEntity.getId(), categoryJpaEntity.getName(),
                categoryJpaEntity.getSlug(), categoryJpaEntity.getImageUrl());
        model.addAttribute("form", categoryForm);
        return "categories/updateCategoryForm";
    }

    @PostMapping(value = "/category/{categoryId}/edit")
    public String updateCategory(@Valid @ModelAttribute("form") CategoryForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "categories/updateCategoryForm";
        }

        updateCategory.update(form);
        return "redirect:/categories";
    }
}