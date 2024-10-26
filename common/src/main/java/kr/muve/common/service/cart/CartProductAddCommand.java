package kr.muve.common.service.cart;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartProductAddCommand {

    private Long userId;
    private Long productId;
    private Integer count;

    public CartProductAddCommand() {}
}
