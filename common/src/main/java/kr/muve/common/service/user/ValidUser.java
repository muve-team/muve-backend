package kr.muve.common.service.user;

import jakarta.servlet.http.HttpServletRequest;

public interface ValidUser {
    ValidUserRes valid(HttpServletRequest request);
}