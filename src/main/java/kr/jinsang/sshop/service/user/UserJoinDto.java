package kr.jinsang.sshop.service.user;

import kr.jinsang.sshop.controller.UserForm;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class UserJoinDto {

    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String city;
    private String street;
    private String zipcode;



    public static UserJoinDto from(UserForm form) {
        return new UserJoinDto(form.getName(), form.getEmail(), form.getPassword(), form.getPhoneNumber(),
                form.getCity(), form.getStreet(), form.getZipcode());
    }

}

