package kr.muve.api.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import kr.muve.common.exception.CommonResponse;
import kr.muve.common.service.user.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserRestController {

    private final JoinUser joinUser;
    private final LoginUser loginUser;
    private final ValidUser validUser;
    private final LogoutUser logoutUser;

    @PostMapping("/join")
    public CommonResponse<JoinUserRes> joinUser(@RequestBody UserJoinCommand command) {
        log.info("[POST] /user/join, name: {}, email: {}, phoneNumber: {}, " +
                        "city: {}, street: {}, zipcode: {}",
                command.getName(), command.getEmail(), command.getPhoneNumber(),
                command.getCity(), command.getCity(), command.getZipcode());

        return CommonResponse.success(joinUser.join(command));
    }

    @PostMapping("/login")
    public CommonResponse<LoginUserRes> loginUser(@RequestBody @Valid UserLoginCommand command, HttpServletResponse response) {

        log.info("[POST] /user/login, email: {}, password: {}", command.getEmail(), command.getPassword());

        return CommonResponse.success(loginUser.login(command, response));
    }

    // JWT 토큰 확인
    @GetMapping("/valid")
    public CommonResponse<ValidUserRes> validUser(HttpServletRequest request) {

        log.info("[GET] /user/valid");

        return CommonResponse.success(validUser.valid(request));
    }

    @GetMapping("/logout")
    public CommonResponse logoutUser(HttpServletRequest request, HttpServletResponse response) {
        log.info("[GET] /user/logout");

        return CommonResponse.success(logoutUser.logout(request, response));
    }

    // TODO: 회원정보 수정
    // @PutMapping
}
