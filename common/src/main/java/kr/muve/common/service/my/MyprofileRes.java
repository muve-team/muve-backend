package kr.muve.common.service.my;

import kr.muve.common.domain.user.Address;
import kr.muve.common.domain.user.UserJpaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MyprofileRes {

    private Long myId;
    private String name;
    private String email;
    private String phoneNumber;
    private Address address;

    public static MyprofileRes from(UserJpaEntity user) {
        return new MyprofileRes(user.getId(), user.getName(), user.getEmail(), user.getPhoneNumber(), user.getAddress());
    }

}
