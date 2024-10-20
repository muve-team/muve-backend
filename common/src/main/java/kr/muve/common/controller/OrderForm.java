package kr.muve.common.controller;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderForm {

    @NotNull(message = "유저 아이디를 작성해주세요")
    @Positive(message = "유저 아이디는 0일 수 없습니다")
    private Long userId;
    @NotNull(message = "상품 아이디를 작성해주세요")
    @Positive(message = "상품 아이디는 0일 수 없습니다")
    private Long productId;
    @NotNull(message = "상품 갯수를 입력해주세요")
    @Positive(message = "갯수는 0일 수 없습니다")
    private Integer count;

}
