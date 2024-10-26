package kr.muve.common.service.cart;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartProductUpdateCountCommand {

    private Long cartProductId;
    private Integer count;

    public CartProductUpdateCountCommand() {}
}
