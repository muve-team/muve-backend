package kr.muve.api.user.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import kr.muve.common.exception.CommonResponse;
import kr.muve.common.service.user.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserRestController {

    private final JoinUser joinUser;
    private final LoginUser loginUser;

    @PostMapping("/join")
    public CommonResponse<Long> joinUser(@RequestBody UserJoinCommand command) {
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

    // TODO: 회원정보 수정
    // @PutMapping
}
