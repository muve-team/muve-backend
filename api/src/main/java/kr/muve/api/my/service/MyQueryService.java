package kr.muve.api.my.service;

import kr.muve.common.domain.user.UserJpaEntity;
import kr.muve.common.exception.UserNotFoundException;
import kr.muve.common.repository.user.SpringDataUserRepository;
import kr.muve.common.service.my.FindMyProfiles;
import kr.muve.common.service.my.MyprofileRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MyQueryService implements FindMyProfiles {

        private final SpringDataUserRepository userRepository;

        @Override
        public MyprofileRes findMyProfile(Long id) {
                return MyprofileRes.from(userRepository.findById(id)
                        .orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다.")));
        }
}