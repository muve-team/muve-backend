package kr.jinsang.sshop.controller;

import jakarta.validation.Valid;
import kr.jinsang.sshop.repository.order.OrderSearch;
import kr.jinsang.sshop.service.order.OrderDto;
import kr.jinsang.sshop.service.order.OrderService;
import kr.jinsang.sshop.service.product.ProductService;
import kr.jinsang.sshop.service.user.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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






}
