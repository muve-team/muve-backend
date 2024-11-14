package kr.muve.admin.controller;

import jakarta.validation.Valid;
import kr.muve.common.controller.OrderForm;
import kr.muve.common.repository.order.OrderSearch;
import kr.muve.common.service.order.CancelOrder;
import kr.muve.common.service.order.CreateOrder;
import kr.muve.common.service.order.FindOrders;
import kr.muve.common.service.order.OrderDto;
import kr.muve.common.service.product.FindProducts;
import kr.muve.common.service.user.FindUsers;
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

//    private final CreateOrder createOrder;

    private final FindOrders findOrders;

    private final CancelOrder cancelOrder;

    private final FindUsers findUsers;

    private final FindProducts findProducts;

    @GetMapping(value = "/order")
    public String createOrderForm(Model model) {
        model.addAttribute("form", new OrderForm());
        model.addAttribute("users", findUsers.findUsers());
        model.addAttribute("products", findProducts.findProducts());
        return "order/createOrderForm";
    }

//    @PostMapping(value = "/order")
//    public String createOrderForm(@Valid OrderForm form, BindingResult result) {
//        if (result.hasErrors()) {
//            return "order/createOrderForm";
//        }
//        OrderDto dto = OrderDto.from(form);
//        createOrder.create(dto);
//
//        return "redirect:/";
//    }

    @GetMapping(value = "/orders")
    public String getOrders(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {
        model.addAttribute("orders", findOrders.findOrders(orderSearch));
        return "order/orderList";
    }

    @PostMapping(value = "/orders/{id}/cancel")
    public String cancelOrder(@PathVariable("id") Long id) {
        cancelOrder.cancelOrder(id);
        return "redirect:/orders";
    }








}
