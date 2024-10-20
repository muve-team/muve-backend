package kr.muve.admin.service.user;

import kr.muve.common.repository.user.UserRepository;
import kr.muve.common.domain.user.User;
import kr.muve.common.service.user.JoinUser;
import kr.muve.common.service.user.UserJoinDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements JoinUser {
    
    private final UserRepository userRepository;

    // 회원 가입
    @Override
    @Transactional
    public Long join(UserJoinDto dto) {

        User user = User.createUser(dto);
        userRepository.save(user);
        
        return user.getId();
    }

    // 회원 전체 조회
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    // 회원 조회
    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    // 회원 수정
}
