package kr.muve.common.service.cart;

import kr.muve.common.domain.cartProduct.CartProductJpaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CartProductsDeleteCommand {

    List<Long> ids;

}
