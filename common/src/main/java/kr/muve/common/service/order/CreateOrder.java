package kr.muve.common.service.order;

import jakarta.servlet.http.HttpServletRequest;
import kr.muve.common.service.user.UserLoginCommand;

public interface CreateOrder {
//    Long create(OrderDto dto);
    Long create(OrderCreateCommand command, HttpServletRequest request);
}
