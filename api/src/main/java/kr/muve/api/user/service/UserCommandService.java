package kr.muve.api.user.service;

import kr.muve.api.security.JwtTokenProvider;
import kr.muve.common.domain.user.UserJpaEntity;
import kr.muve.common.exception.UserNotFoundException;
import kr.muve.common.repository.user.SpringDataUserRepository;
import kr.muve.common.service.user.JoinUser;
import kr.muve.common.service.user.LoginUser;
import kr.muve.common.service.user.UserJoinCommand;
import kr.muve.common.service.user.UserLoginCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserCommandService implements JoinUser, LoginUser {

    private final SpringDataUserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public Long join(UserJoinCommand command) {
        UserJpaEntity user = UserJpaEntity.createUser(command);
        userRepository.save(user);
        return user.getId();
    }


    @Override
    public String login(UserLoginCommand command) {
        String email = command.getEmail();
        String password = command.getPassword();

        UserJpaEntity foundUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("등록되지 않은 회원입니다."));

        if (!foundUser.validatePassword(password)) {
            throw new UserNotFoundException("회원 정보가 올바르지 않습니다.");
        }

        return jwtTokenProvider.createToken(email);
    }
}
