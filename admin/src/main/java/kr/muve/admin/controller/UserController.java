package kr.muve.admin.controller;

import jakarta.validation.Valid;
import kr.muve.admin.service.user.UserService;
import kr.muve.common.controller.UserForm;
import kr.muve.common.service.user.UserJoinDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users/join")
    public String joinForm(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "users/createUserForm";

    }

    @PostMapping("/users/join")
    public String join(@Valid UserForm userForm, BindingResult result) {
        if (result.hasErrors()) {
            return "users/createUserForm";
        }

        UserJoinDto dto = UserJoinDto.from(userForm);
        userService.join(dto);
        return "redirect:/";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.findUsers());
        return "users/userList";
    }
}
