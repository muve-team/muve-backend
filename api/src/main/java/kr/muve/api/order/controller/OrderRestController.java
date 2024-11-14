package kr.muve.api.order.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import kr.muve.common.exception.CommonResponse;
import kr.muve.common.service.order.CreateOrder;
import kr.muve.common.service.order.DetailOrder;
import kr.muve.common.service.order.OrderCreateCommand;
import kr.muve.common.service.order.OrderDetailRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderRestController {

    private final DetailOrder detailOrder;
    private final CreateOrder createOrder;

    @GetMapping(value = "/detail")
    public CommonResponse<OrderDetailRes> getOrderDetail(@RequestParam("orderId") Long orderId) {

        log.info("[GET] /order/detail, orderId: {}", orderId);

        return CommonResponse.success(detailOrder.getOrderDetail(orderId));
    }

    @PostMapping
    public CommonResponse<Long> createOrder(@RequestBody @Valid OrderCreateCommand command, HttpServletRequest request) {

        log.info("[POST] /order, productId: {}, ordererName: {}, ordererPhoneNumber: {}, ordererEmail: {}, receiverName: {}, " +
                "receiverPhoneNumber: {}, city: {}, street: {}, zipcode: {}, paymentMethod: {}",
                command.productId(), command.ordererName(), command.ordererPhoneNumber(), command.ordererEmail(), command.receiverName(),
                command.receiverPhoneNumber(), command.city(), command.street(), command.zipcode(), command.paymentMethod());

        return CommonResponse.success(createOrder.create(command, request));
    }
}
