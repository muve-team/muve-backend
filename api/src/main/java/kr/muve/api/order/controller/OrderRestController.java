package kr.muve.api.order.controller;

import kr.muve.common.service.order.DetailOrder;
import kr.muve.common.service.order.OrderDetailRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderRestController {

    private final DetailOrder detailOrder;

    @GetMapping(value = "/detail")
    public OrderDetailRes getOrderDetail(@RequestParam("orderId") Long orderId) {
        return detailOrder.getOrderDetail(orderId);
    }
}
