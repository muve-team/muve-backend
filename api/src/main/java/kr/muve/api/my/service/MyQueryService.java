package kr.muve.api.my.service;

import kr.muve.common.domain.order.OrderJpaEntity;
import kr.muve.common.domain.saved.SavedJpaEntity;
import kr.muve.common.domain.savedProduct.SavedProductJpaEntity;
import kr.muve.common.domain.user.UserJpaEntity;
import kr.muve.common.exception.UserNotFoundException;
import kr.muve.common.repository.order.SpringDataOrderRepository;
import kr.muve.common.repository.saved.SpringDataSavedRepository;
import kr.muve.common.repository.user.SpringDataUserRepository;
import kr.muve.common.service.my.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<MyOrderRes> findMyAllOrders(Long id, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return MyOrderRes.from(orderRepository.findAllByUserId(id, pageable).getContent());
    }

    @Override
    public List<MySavedProductRes> findMySaved(Long userId, Integer page, Integer size) {
        // SavedJpaEntity 가져오기 (나의 찜)
        SavedJpaEntity savedJpaEntity = savedRepository.findByUserId(userId);

        // savedProductJpaEntities만 꺼내서 리스트를 페이징 처리
        List<SavedProductJpaEntity> savedProducts = savedJpaEntity.getSavedProductJpaEntities();
        Pageable pageable = PageRequest.of(page, size);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), savedProducts.size());

        // 리스트가 페이징 범위 안에 있을 때만 잘라서 반환
        List<SavedProductJpaEntity> pagedSavedProducts = savedProducts.subList(start, end);

        return MySavedProductRes.from(pagedSavedProducts);
    }
}