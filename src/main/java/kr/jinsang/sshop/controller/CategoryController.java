package kr.jinsang.sshop.controller;

import jakarta.validation.Valid;
import kr.jinsang.sshop.domain.category.Category;
import kr.jinsang.sshop.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping(value = "/category/new")
    public String createCategoryForm(Model model) {
        model.addAttribute("categoryForm", new CategoryForm());
        return "category/createCategoryForm";
    }

    @PostMapping(value = "/category/new")
    public String create(@Valid CategoryForm categoryForm, BindingResult result) {
        if (result.hasErrors()) {
            return "category/createCategoryForm";
        }
        categoryService.create(categoryForm.getName());
        return "redirect:/";
    }

    @GetMapping(value = "/categories")
    public String getCategories(Model model) {
        model.addAttribute("categories", categoryService.findCategories());
        return "category/categoryList";
    }

    @GetMapping(value = "/category/{categoryId}/edit")
    public String updateCategoryForm(@PathVariable Long categoryId, Model model) {
        Category category = categoryService.findOne(categoryId);
        CategoryForm categoryForm = CategoryForm.categoryForm(category.getId(), category.getName());
        model.addAttribute("form", categoryForm);
        return "category/updateCategoryForm";
    }

    @PostMapping(value = "/category/{categoryId}/edit")
    public String updateCategory(@Valid CategoryForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "/category/updateCategoryForm";
        }
        categoryService.update(form);
        return "redirect:/categories";
    }
}
