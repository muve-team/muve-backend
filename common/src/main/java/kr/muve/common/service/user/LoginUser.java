package kr.muve.common.service.user;

import jakarta.servlet.http.HttpServletResponse;

public interface LoginUser {
    String login(UserLoginCommand command, HttpServletResponse response);
}