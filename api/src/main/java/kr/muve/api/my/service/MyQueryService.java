package kr.muve.api.my.service;

import kr.muve.common.domain.saved.SavedJpaEntity;
import kr.muve.common.exception.UserNotFoundException;
import kr.muve.common.repository.order.SpringDataOrderRepository;
import kr.muve.common.repository.saved.SpringDataSavedRepository;
import kr.muve.common.repository.user.SpringDataUserRepository;
import kr.muve.common.service.my.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MyQueryService implements FindMyProfiles, FindMyOrders, FindMySaved {

    private final SpringDataUserRepository userRepository;
    private final SpringDataOrderRepository orderRepository;
    private final SpringDataSavedRepository savedRepository;

    @Override
    public MyProfileRes findMyProfile(Long id) {
        return MyProfileRes.from(userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다.")));
    }

    @Override
    public List<MyOrderRes> findMyAllOrders(Long id) {
        return MyOrderRes.from(orderRepository.findAllById(Arrays.asList(id)));
    }

    @Override
    public List<MySavedProductRes> findMySaved(Long userId) {
        SavedJpaEntity savedJpaEntity = savedRepository.findByUserId(userId);
        return MySavedProductRes.from(savedJpaEntity.getSavedProductJpaEntities());
    }
}