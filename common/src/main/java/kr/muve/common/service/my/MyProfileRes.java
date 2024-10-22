package kr.muve.common.service.my;

import kr.muve.common.domain.user.Address;
import kr.muve.common.domain.user.UserJpaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MyProfileRes {

    private Long myId;
    private String name;
    private String email;
    private String phoneNumber;
    private Address address;

    public static MyProfileRes from(UserJpaEntity user) {
        return new MyProfileRes(user.getId(), user.getName(), user.getEmail(), user.getPhoneNumber(), user.getAddress());
    }

}
