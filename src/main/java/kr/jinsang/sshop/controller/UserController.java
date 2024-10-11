package kr.jinsang.sshop.controller;

import kr.jinsang.sshop.service.user.UserJoinDto;
import kr.jinsang.sshop.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String join(UserForm userForm) {
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
