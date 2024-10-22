package kr.muve.api.my.service;

import kr.muve.common.domain.user.UserJpaEntity;
import kr.muve.common.exception.UserNotFoundException;
import kr.muve.common.repository.order.SpringDataOrderRepositoryCustom;
import kr.muve.common.repository.user.SpringDataUserRepository;
import kr.muve.common.service.my.FindMyOrders;
import kr.muve.common.service.my.FindMyProfiles;
import kr.muve.common.service.my.MyOrderRes;
import kr.muve.common.service.my.MyprofileRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MyQueryService implements FindMyProfiles, FindMyOrders {

        private final SpringDataUserRepository userRepository;
        private final SpringDataOrderRepositoryCustom orderRepository;

        @Override
        public MyprofileRes findMyProfile(Long id) {
                return MyprofileRes.from(userRepository.findById(id)
                        .orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다.")));
        }

        @Override
        public List<MyOrderRes> findMyAllOrders(Long id) {
                return MyOrderRes.from(orderRepository.findAllById(Arrays.asList(id)));
        }
}