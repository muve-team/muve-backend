package kr.muve.common.exception;

public class JweTokenInvalidException extends RuntimeException {
    public JweTokenInvalidException() {
        super();
    }

    public JweTokenInvalidException(String message) {
        super(message);
    }

    public JweTokenInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public JweTokenInvalidException(Throwable cause) {
        super(cause);
    }

    protected JweTokenInvalidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
