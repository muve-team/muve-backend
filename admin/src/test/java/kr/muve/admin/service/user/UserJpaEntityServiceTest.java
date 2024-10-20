package kr.muve.admin.service.user;

import jakarta.persistence.EntityManager;
import kr.muve.admin.service.UserService;
import kr.muve.common.domain.user.UserJpaEntity;
import kr.muve.common.service.user.UserJoinDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class UserJpaEntityServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    EntityManager em;


    // 저장이 되었는지
    @Test
    void 회원가입() {
        // 주어진 값들
        UserJoinDto dto = new UserJoinDto("a", "a@naver.com", "1234", "010-1111-1111",
                "a동", "3001호", "2345");

        // 테스트 수행
        Long userId = userService.join(dto);
        UserJpaEntity foundUserJpaEntity = em.find(UserJpaEntity.class, 1L);

        // 테스트 검증
        assertThat(userId).isEqualTo(foundUserJpaEntity.getId());

    }


}