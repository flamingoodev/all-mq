package messagequeue.exception;

/**
 * 不支持参数异常
 *
 * @author FLAMINGO
 * @since 2020/4/20 13:14
 */
public class ConnectionConfigErrorException extends RuntimeException {

    private static final long serialVersionUID = -6224870864040940424L;

    public ConnectionConfigErrorException() {
    }

    public ConnectionConfigErrorException(String message) {
        super(message);
    }

    public ConnectionConfigErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionConfigErrorException(Throwable cause) {
        super(cause);
    }

    public ConnectionConfigErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
