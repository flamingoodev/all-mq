package io.rushb.allmq.exception;

/**
 * @author zxj<br>
 * 时间 2018/3/19 17:32
 * 说明 ...
 */
public class NotSupportParamException extends RuntimeException {

    public NotSupportParamException() {
    }

    public NotSupportParamException(String message) {
        super(message);
    }

    public NotSupportParamException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotSupportParamException(Throwable cause) {
        super(cause);
    }

    public NotSupportParamException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
