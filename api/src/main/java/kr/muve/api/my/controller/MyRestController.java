package kr.muve.api.my.controller;

import kr.muve.common.service.my.FindMyOrders;
import kr.muve.common.service.my.FindMyProfiles;
import kr.muve.common.service.my.MyOrderRes;
import kr.muve.common.service.my.MyprofileRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/my")
public class MyRestController {

    private final FindMyProfiles findMyProfiles;
    private final FindMyOrders findMyOrders;

    // 내 프로필 조회
    @GetMapping(value = "/profile")
    public MyprofileRes getMyProfile(@PathVariable("userId") Long userId) {
        return findMyProfiles.findMyProfile(userId);
    }

    // 주문 전체 조회 (주문, 취소, 환불 나눌 예정)
    @GetMapping(value = "/orders")
    public List<MyOrderRes> getMyOrders(@PathVariable("userId") Long userId)  {
        return findMyOrders.findMyAllOrders(userId);
    }
//
//    @GetMapping(value = "/saved")
//    public void getMySaved() {
//
//    }
}
