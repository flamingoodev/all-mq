package messagequeue.exception;

/**
 * 不支持参数异常
 *
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/5 22:14
 */
public class NotSupportParamException extends RuntimeException {

    private static final long serialVersionUID = -6224870864040940424L;

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
