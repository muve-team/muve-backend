package kr.muve.common.service.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidUserProfileRes {
    private String email;
    private String name;
}
