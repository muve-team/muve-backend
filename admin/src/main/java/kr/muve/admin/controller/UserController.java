package kr.muve.admin.controller;

import kr.muve.common.service.user.FindUsers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final FindUsers findUsers;

//    @GetMapping("/users/join")
//    public String joinForm(Model model) {
//        model.addAttribute("userForm", new UserForm());
//        return "users/createUserForm";
//    }
//
//    @PostMapping("/users/join")
//    public String join(@Valid UserForm userForm, BindingResult result) {
//        if (result.hasErrors()) {
//            return "users/createUserForm";
//        }
//
//        UserJoinDto dto = UserJoinDto.from(userForm);
//        joinUser.join(dto);
//        return "redirect:/";
//    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", findUsers.findUsers());
        return "users/userList";
    }
}
