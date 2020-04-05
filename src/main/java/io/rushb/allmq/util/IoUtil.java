package io.rushb.allmq.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * IO工具类
 *
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/5 22:14
 */
public class IoUtil {
    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                closeable = null;
            }
        }
    }
}
