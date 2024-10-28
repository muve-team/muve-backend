package kr.muve.api.order.controller;

import kr.muve.common.exception.CommonResponse;
import kr.muve.common.service.order.DetailOrder;
import kr.muve.common.service.order.OrderDetailRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderRestController {

    private final DetailOrder detailOrder;

    @GetMapping(value = "/detail")
    public CommonResponse<OrderDetailRes> getOrderDetail(@RequestParam("orderId") Long orderId) {

        log.info("[GET] /order/detail, orderId: {}", orderId);

        return CommonResponse.success(detailOrder.getOrderDetail(orderId));
    }
}
