package kr.jinsang.sshop.controller;

import lombok.Data;

@Data
public class UserForm {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String city;
    private String street;
    private String zipcode;
}
