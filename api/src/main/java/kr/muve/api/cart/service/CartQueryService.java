package kr.muve.api.cart.service;

import kr.muve.common.domain.cart.CartJpaEntity;
import kr.muve.common.domain.cartProduct.CartProductJpaEntity;
import kr.muve.common.repository.cart.SpringDataCartRepository;
import kr.muve.common.service.cart.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartQueryService implements ProductsCart{

    private final SpringDataCartRepository cartRepsitory;

    @Override
    public List<CartProducts> getCartProducts(Long userId, Integer page, Integer size) {
        // CartJpaEntity 가져오기 (나의 장바구니)
        CartJpaEntity cartJpaEntity = cartRepsitory.findByUserId(userId);

        // 상품들만 꺼내서 페이징 처리
        List<CartProductJpaEntity> cartProducts = cartJpaEntity.getCartProductJpaEntities();
        Pageable pageable = PageRequest.of(page, size);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), cartProducts.size());

        // 리스트가 페이징 범위 안에 있을 때문 잘라서 반환
        List<CartProductJpaEntity> pagedCartProducts = cartProducts.subList(start, end);

        return CartProducts.from(pagedCartProducts);
    }


}
