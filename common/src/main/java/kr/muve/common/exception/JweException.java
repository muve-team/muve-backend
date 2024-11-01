package kr.muve.common.exception;

import lombok.Getter;

/**
 * BaseException 또는 BaseException 을 확장한 Exception 은
 * 서비스 운영에서 예상이 가능한 Exception 을 표현한다.
 *
 * 그렇기 때문에 http status: 200 이면서 result: FAIL 을 표현하고
 * 특정 ErrorCode 만 alert 를 포함한 모니터링 대상으로 한다.
 */

import lombok.Getter;

@Getter
public class JweException extends BaseException {

    public JweException() {
        super();
    }

    public JweException(ErrorCode errorCode) {
        super(errorCode);
    }

    public JweException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public JweException(String message, ErrorCode errorCode, Throwable cause) {
        super(message, errorCode, cause);
    }
}
