package kr.muve.common.service.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class SellerLoginCommand {
    @NotEmpty(message = "이메일을 포함해야 합니다.")
    private String email;

    @NotEmpty(message = "비밀번호를 포함해야 합니다.")
    private String password;
}

