package kr.muve.api.home;

import kr.muve.common.exception.CommonResponse;
import kr.muve.common.service.my.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/")
public class HomeController {

    // 내 프로필 조회
    @GetMapping
    public CommonResponse<String> home() {
        return CommonResponse.success("OK");
    }
}