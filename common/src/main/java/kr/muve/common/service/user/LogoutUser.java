package kr.muve.common.service.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface LogoutUser {
    boolean logout(HttpServletRequest request, HttpServletResponse response);
}