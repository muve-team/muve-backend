package kr.muve.api.user.controller;

import kr.muve.common.service.user.JoinUser;
import kr.muve.common.service.user.LoginUser;
import kr.muve.common.service.user.UserJoinCommand;
import kr.muve.common.service.user.UserLoginCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserRestController {

    private final JoinUser joinUser;
    private final LoginUser loginUser;

    @PostMapping("/join")
    public Long joinUser(@RequestBody UserJoinCommand command) {
        return joinUser.join(command);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody UserLoginCommand command) {
        return loginUser.login(command);
    }

    // TODO: 회원정보 수정
    // @PutMapping
}
