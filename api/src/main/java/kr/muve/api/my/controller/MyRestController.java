package kr.muve.api.my.controller;

import kr.muve.common.exception.CommonResponse;
import kr.muve.common.service.my.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/my")
public class MyRestController {

    private final FindMyProfiles findMyProfiles;
    private final FindMyOrders findMyOrders;
    private final FindMySaved findMySaved;

    // 내 프로필 조회
    @GetMapping(value = "/profile")
    public CommonResponse<MyProfileRes> getMyProfile(@RequestParam("userId") Long userId) {

        log.info("[GET] /my/profile, userId: {}", userId);

        return CommonResponse.success(findMyProfiles.findMyProfile(userId));
    }

    // 주문 전체 조회 (주문, 취소, 환불 나눌 예정)
    @GetMapping(value = "/orders")
    public CommonResponse<List<MyOrderRes>> getMyOrders(@RequestParam("userId") Long userId,
                                        @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                        @RequestParam(name = "size", required = false, defaultValue = "10") Integer size)  {

        log.info("[GET] /my/orders, userId: {}, page: {}, size: {}", userId, page, size);

        return CommonResponse.success(findMyOrders.findMyAllOrders(userId, page, size));
    }

    @GetMapping(value = "/saved")
    public CommonResponse<List<MySavedProductRes>> getMySaved(@RequestParam("userId") Long userId,
                                              @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                              @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {

        log.info("[GET] /my/saved, userId: {}, page: {}, size: {}", userId, page, size);

        return CommonResponse.success(findMySaved.findMySaved(userId, page, size));
    }
}
