package kr.muve.admin.controller;

import jakarta.validation.Valid;
import kr.muve.common.controller.ImageFileValidator;
import kr.muve.common.domain.product.ProductJpaEntity;
import kr.muve.common.service.category.FindCategories;
import kr.muve.common.service.product.CreateProduct;
import kr.muve.common.service.product.FindProducts;
import kr.muve.common.service.product.ProductDto;
import kr.muve.common.controller.ProductForm;
import kr.muve.common.service.product.UpdateProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final CreateProduct createProduct;

    private final UpdateProduct updateProduct;

    private final FindProducts findProducts;

    private final FindCategories findCategories;

    private final ImageFileValidator imageFileValidator;

    @GetMapping(value = "/products/new")
    public String createProductForm(Model model) {
        model.addAttribute("productForm", new ProductForm());
        model.addAttribute("categories", findCategories.findCategories());
        return "products/createProductForm";
    }

    @PostMapping(value = "/products/new", consumes = { "multipart/form-data" })
    public String create(@Valid ProductForm form, BindingResult result, Errors errors, Model model) {
        // 이미지 검증
        imageFileValidator.validate(form.getImage(), result);

        if (result.hasErrors() || errors.hasErrors()) {
            model.addAttribute("categories", findCategories.findCategories());
            return "products/createProductForm";
        }

        ProductDto dto = ProductDto.from(form);
        createProduct.create(dto);
        return "redirect:/";
    }

    @GetMapping(value = "/products")
    public String getProducts(Model model) {
        model.addAttribute("products", findProducts.findProducts());
        return "products/productList";
    }

    @GetMapping(value = "/products/{productId}/edit")
    public String updateProductForm(@PathVariable("productId") Long productId, Model model) {
        ProductJpaEntity productJpaEntity = findProducts.findById(productId);
        ProductForm form = ProductForm.from(productJpaEntity);
        model.addAttribute("form", form);
        model.addAttribute("categories", findCategories.findCategories());
        return "products/updateProductForm";
    }

    @PostMapping(value = "/products/{productId}/edit")
    public String updateProduct(@Valid ProductForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "/products/updateProductForm";
        }

        ProductDto dto = ProductDto.from(form);
        updateProduct.update(dto);
        return "redirect:/products";
    }
}
