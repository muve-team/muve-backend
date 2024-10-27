package kr.muve.api.cart.service;

import kr.muve.common.domain.cart.CartJpaEntity;
import kr.muve.common.domain.cartProduct.CartProductJpaEntity;
import kr.muve.common.domain.product.ProductJpaEntity;
import kr.muve.common.domain.user.UserJpaEntity;
import kr.muve.common.exception.CartProductNotFoundException;
import kr.muve.common.exception.ProductNotFoundException;
import kr.muve.common.exception.UserNotFoundException;
import kr.muve.common.repository.cart.SpringDataCartRepository;
import kr.muve.common.repository.cartProduct.SpringDataCartProductRepository;
import kr.muve.common.repository.product.SpringDataProductRepository;
import kr.muve.common.repository.user.SpringDataUserRepository;
import kr.muve.common.service.cart.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class CartCommandService implements AddCartProduct, UpdateCartProductCount, DeleteCartProducts {

    private final SpringDataProductRepository productRepository;
    private final SpringDataUserRepository userRepository;
    private final SpringDataCartRepository cartRepository;
    private final SpringDataCartProductRepository cartProductRepository;
    @Override
    public Long add(CartProductAddCommand command) {
        UserJpaEntity user = userRepository.findById(command.getUserId())
                .orElseThrow(() -> new UserNotFoundException("사용자 정보를 찾을 수 없습니다."));
        CartJpaEntity cart = cartRepository.findByUserId(user.getId());
        ProductJpaEntity foundProduct = productRepository.findById(command.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("상품 정보를 찾을 수 없습니다."));

        // cartProduct
        CartProductJpaEntity cartProductJpaEntity = CartProductJpaEntity.createCartProduct(cart, foundProduct, command.getCount());
        cartProductRepository.save(cartProductJpaEntity);
        return cart.getId();
    }

    @Override
    public Long update(CartProductUpdateCountCommand command) {
        CartProductJpaEntity cartProduct = getCartProduct(command.getCartProductId());
        cartProduct.updateCount(command.getCount());
        return cartProduct.getId();
    }

    @Override
    public Long delete(CartProductsDeleteCommand command) {
        CartProductJpaEntity cartProductJpaEntity = getCartProduct(command.getIds().get(0));
        Long cartId = cartProductJpaEntity.getCartJpaEntity().getId();
        cartProductRepository.deleteAllById(command.getIds());
        return cartId;
    }

    private CartProductJpaEntity getCartProduct(Long cartProductId) {
        CartProductJpaEntity cartProduct = cartProductRepository.findById(cartProductId)
                .orElseThrow(() -> new CartProductNotFoundException("카트에 해당 상품이 담겨있지 않습니다."));
        return cartProduct;
    }

}
