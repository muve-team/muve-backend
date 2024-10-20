package kr.muve.admin.controller;

import jakarta.validation.Valid;
import kr.muve.common.controller.OrderForm;
import kr.muve.common.repository.order.OrderSearch;
import kr.muve.admin.service.order.OrderDto;
import kr.muve.admin.service.order.OrderService;
import kr.muve.admin.service.product.ProductService;
import kr.muve.admin.service.user.UserService;
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
public class OrderController {

    private final OrderService orderService;

    private final UserService userService;

    private final ProductService productService;

    @GetMapping(value = "/order")
    public String createOrderForm(Model model) {
        model.addAttribute("form", new OrderForm());
        model.addAttribute("users", userService.findUsers());
        model.addAttribute("products", productService.findProducts());
        return "order/createOrderForm";
    }

    @PostMapping(value = "/order")
    public String createOrderForm(@Valid OrderForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "order/createOrderForm";
        }
        OrderDto dto = OrderDto.from(form);
        orderService.create(dto);

        return "redirect:/";
    }

    @GetMapping(value = "/orders")
    public String getOrders(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {
        model.addAttribute("orders", orderService.findOrders(orderSearch));
        return "order/orderList";
    }

    @PostMapping(value = "/orders/{id}/cancel")
    public String cancelOrder(@PathVariable Long id) {
        orderService.cancelOrder(id);
        return "redirect:/orders";
    }








}
