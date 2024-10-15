package kr.jinsang.sshop.controller;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserForm {
    @NotBlank(message = "이름은 필수 값 입니다.")
    private String name;
    @NotBlank(message = "이메일은 필수 값 입니다.")
    @Email(message = "이메일 형식으로 작성해주세요.")
    private String email;
    @NotBlank(message = "비밀번호는 필수 값 입니다.")
    private String password;
    private String phoneNumber;
    private String city;
    private String street;
    private String zipcode;
}
