package kr.jinsang.sshop.controller;

import jakarta.validation.Valid;
import kr.jinsang.sshop.domain.product.Product;
import kr.jinsang.sshop.service.product.ProductDto;
import kr.jinsang.sshop.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping(value = "/products/new")
    public String createProductForm(Model model) {
        model.addAttribute("productForm", new ProductForm());
        return "products/createProductForm";
    }

    @PostMapping(value = "/products/new")
    public String create(@Valid ProductForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "products/createProductForm";
        }
        ProductDto dto = ProductDto.from(form);
        productService.create(dto);
        return "redirect:/";
    }

    @GetMapping(value = "/products")
    public String getProducts(Model model) {
        model.addAttribute("products", productService.findProducts());
        return "products/productList";
    }

    @GetMapping(value = "/products/{productId}/edit")
    public String updateProductForm(@PathVariable Long productId, Model model) {
        Product product = productService.findOne(productId);
        ProductForm form = ProductForm.from(product);
        model.addAttribute("form", form);
        return "products/updateProductForm";
    }

    @PostMapping(value = "/products/{productId}/edit")
    public String updateProduct(@Valid ProductForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "/products/updateProductForm";
        }

        ProductDto dto = ProductDto.from(form);
        productService.update(dto);
        return "redirect:/products";
    }
}
