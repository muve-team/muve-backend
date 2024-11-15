package kr.muve.common.service.order;

import jakarta.servlet.http.HttpServletRequest;

public interface DetailOrder {

    OrderDetailRes getOrderDetail(Long orderId, HttpServletRequest request);
}
