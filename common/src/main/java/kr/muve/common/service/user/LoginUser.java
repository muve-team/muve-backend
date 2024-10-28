package kr.muve.common.service.user;

import jakarta.servlet.http.HttpServletResponse;

public interface LoginUser {
    LoginUserRes login(UserLoginCommand command, HttpServletResponse response);
}