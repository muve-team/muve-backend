package kr.muve.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    COMMON_SYSTEM_ERROR("일시적인 오류가 발생했습니다. 잠시 후 다시 시도해주세요."), // 장애 상황
    COMMON_INVALID_PARAMETER("요청한 값이 올바르지 않습니다."),
    COMMON_ENTITY_NOT_FOUND("존재하지 않는 엔티티입니다."),
    COMMON_ILLEGAL_STATUS("잘못된 상태값입니다."),

    // API
    JWE_TOKEN_INVALID("토큰이 유효하지 않습니다."),
    JWE_CYPHER_FAIL("토큰 암복호화에 실패하였습니다."),
    JWE_EXPIRED("토큰이 만료되었습니다."),
    CART_PRODUCT_NOT_FOUND("카트에 해당 상품이 담겨있지 않습니다."),
    USER_NOT_FOUND("사용자 정보를 찾을 수 없습니다."),
    PRODUCT_NOT_FOUND("상품 정보를 찾을 수 없습니다."),
    CATEGORY_NOT_FOUND("카테고리를 찾을 수 없습니다."),
    ORDER_NOT_FOUND("주문 내역이 없습니다."),
    USER_ALREADY_EXIST("사용자가 이미 존재합니다.");


    private final String errorMsg;

    public String getErrorMsg(Object... arg) {
        return String.format(errorMsg, arg);
    }
}
