package kr.muve.api.my.controller;

import kr.muve.common.service.my.FindMyProfiles;
import kr.muve.common.service.my.MyprofileRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/my")
public class MyRestController {

    private final FindMyProfiles findMyProfiles;

    @GetMapping(value = "/profile")
    public MyprofileRes getMyProfile(@PathVariable("userId") Long userId) {
        return findMyProfiles.findMyProfile(userId);
    }

//    @GetMapping(value = "/orders")
//    public void getMyOrders() {
//
//    }
//
//    @GetMapping(value = "/saved")
//    public void getMySaved() {
//
//    }
}
