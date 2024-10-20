package kr.muve.admin.service;

import kr.muve.common.domain.user.UserJpaEntity;
import kr.muve.common.domain.user.UserJpaEntity;
import kr.muve.common.exception.UserNotFoundException;
import kr.muve.common.repository.user.SpringDataUserRepository;
import kr.muve.common.service.user.FindUsers;
import kr.muve.common.service.user.JoinUser;
import kr.muve.common.service.user.UserJoinDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements JoinUser, FindUsers {
    
    private final SpringDataUserRepository userRepository;

    // 회원 가입
    @Override
    @Transactional
    public Long join(UserJoinDto dto) {

        UserJpaEntity userJpaEntity = UserJpaEntity.createUser(dto);
        userRepository.save(userJpaEntity);
        
        return userJpaEntity.getId();
    }

    // 회원 전체 조회
    @Override
    public List<UserJpaEntity> findUsers() {
        return userRepository.findAll();
    }

    // 회원 조회
    @Override
    public UserJpaEntity findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));
    }

}
