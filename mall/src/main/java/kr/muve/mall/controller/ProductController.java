package kr.muve.mall.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.muve.common.domain.product.ProductJpaEntity;
import kr.muve.common.repository.product.SpringDataProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private SpringDataProductRepository productRepository;

    @GetMapping
    public String list(Model model, HttpServletRequest request,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "10") int size) {
        Page<ProductJpaEntity> products = productRepository.findAll(
                PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"))
        );

        model.addAttribute("products", products);
        model.addAttribute("currentUri", request.getRequestURI());
        return "products/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model, HttpServletRequest request) {
        ProductJpaEntity product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));

        model.addAttribute("product", product);
        model.addAttribute("currentUri", request.getRequestURI());
        return "products/detail";
    }

    @GetMapping("/new")
    public String createForm(Model model, HttpServletRequest request) {
        model.addAttribute("product", new ProductJpaEntity());
        model.addAttribute("currentUri", request.getRequestURI());
        return "products/form";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute ProductJpaEntity product, RedirectAttributes redirectAttributes) {
        ProductJpaEntity savedProduct = productRepository.save(product);
        redirectAttributes.addFlashAttribute("message", "상품이 성공적으로 등록되었습니다.");
        return "redirect:/products/" + savedProduct.getId();
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model, HttpServletRequest request) {
        ProductJpaEntity product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));

        model.addAttribute("product", product);
        model.addAttribute("currentUri", request.getRequestURI());
        return "products/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @ModelAttribute ProductJpaEntity product,
                         RedirectAttributes redirectAttributes) {
//        product.setId(id);
        productRepository.save(product);
        redirectAttributes.addFlashAttribute("message", "상품이 성공적으로 수정되었습니다.");
        return "redirect:/products/" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        productRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "상품이 성공적으로 삭제되었습니다.");
        return "redirect:/products";
    }
}