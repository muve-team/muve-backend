package kr.muve.api.my.controller;

import kr.muve.common.service.my.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/my")
public class MyRestController {

    private final FindMyProfiles findMyProfiles;
    private final FindMyOrders findMyOrders;
    private final FindMySaved findMySaved;

    // 내 프로필 조회
    @GetMapping(value = "/profile")
    public MyProfileRes getMyProfile(@RequestParam("userId") Long userId) {
        return findMyProfiles.findMyProfile(userId);
    }

    // 주문 전체 조회 (주문, 취소, 환불 나눌 예정)
    @GetMapping(value = "/orders")
    public List<MyOrderRes> getMyOrders(@RequestParam("userId") Long userId,
                                        @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                        @RequestParam(name = "size", required = false, defaultValue = "10") Integer size)  {
        return findMyOrders.findMyAllOrders(userId, page, size);
    }

    @GetMapping(value = "/saved")
    public List<MySavedProductRes> getMySaved(@RequestParam("userId") Long userId,
                                              @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                              @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        return findMySaved.findMySaved(userId, page, size);
    }
}
