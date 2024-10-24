package kr.muve.common.service.user;

import kr.muve.common.controller.UserForm;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class UserLoginCommand {
    private String email;
    private String password;

    private UserLoginCommand() {}
}

