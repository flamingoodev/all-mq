package io.rushb.allmq.exception;

/**
 * @author zxj<br>
 * 时间 2018/3/19 17:14
 * 说明 ...
 */
public class CreateConnectionFailException extends RuntimeException {

    public CreateConnectionFailException() {
    }

    public CreateConnectionFailException(String message) {
        super(message);
    }

    public CreateConnectionFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreateConnectionFailException(Throwable cause) {
        super(cause);
    }

    public CreateConnectionFailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
