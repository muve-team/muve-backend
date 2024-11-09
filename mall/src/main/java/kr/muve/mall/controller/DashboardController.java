package kr.muve.mall.controller;
import jakarta.servlet.http.HttpServletRequest;
import kr.muve.common.domain.product.ProductJpaEntity;
import kr.muve.common.repository.product.SpringDataProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private SpringDataProductRepository productRepository;

    @GetMapping("/")
    public String dashboard(Model model, HttpServletRequest request) {
        // 총 상품 수
        long totalProducts = productRepository.count();
        model.addAttribute("totalProducts", totalProducts);

        // 총 재고량
//        int totalStock = productRepository.findAll().stream()
//                .mapToInt(ProductJpaEntity::getStock)
//                .sum();
//        model.addAttribute("totalStock", totalStock);

        // 평균 가격
//        BigDecimal averagePrice = BigDecimal.ZERO;
//        if (totalProducts > 0) {
//            averagePrice = productRepository.findAll().stream()
//                    .map(ProductJpaEntity::getPrice)
//                    .reduce(BigDecimal.ZERO, BigDecimal::add)
//                    .divide(BigDecimal.valueOf(totalProducts), 2, RoundingMode.HALF_UP);
//        }
//        model.addAttribute("averagePrice", averagePrice);

        // 최근 등록된 상품 5개
        List<ProductJpaEntity> recentProducts = productRepository.findAll(
                PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "id"))
        ).getContent();
        model.addAttribute("recentProducts", recentProducts);
        model.addAttribute("currentUri", request.getRequestURI());

        return "dashboard/dashboard";
    }
}