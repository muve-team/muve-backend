package kr.muve.common.service.user;

import jakarta.servlet.http.HttpServletResponse;

public interface LoginSeller {
    void login(SellerLoginCommand command, HttpServletResponse response);
}