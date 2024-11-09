package kr.muve.mall.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import kr.muve.common.service.user.LoginSeller;
import kr.muve.common.service.user.SellerLoginCommand;
import kr.muve.common.service.user.UserLoginCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginSeller loginSeller;

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@ModelAttribute @Valid SellerLoginCommand command, HttpServletResponse response) {
        loginSeller.login(command, response);
        return "redirect:/";
    }
}