package io.rushb.allmq.util;

/**
 * Asserts
 *
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/5 22:14
 */
public class Asserts {

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new NullPointerException(message);
        }

    }
}
