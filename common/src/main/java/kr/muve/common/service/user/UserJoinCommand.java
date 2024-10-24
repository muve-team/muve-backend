package kr.muve.common.service.user;

import kr.muve.common.controller.UserForm;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class UserJoinCommand {

    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String city;
    private String street;
    private String zipcode;

    private UserJoinCommand() {}
}

