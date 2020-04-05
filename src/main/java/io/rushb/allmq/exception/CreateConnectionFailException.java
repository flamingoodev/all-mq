package io.rushb.allmq.exception;

/**
 * 连接异常
 *
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/5 22:14
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
