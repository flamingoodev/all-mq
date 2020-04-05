package io.rushb.allmq.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author zxj<br>
 * 时间 2018/3/19 14:41
 * 说明 ...
 */
public class IOUtil {
    public static void close(Closeable closeable){
        if(closeable != null){
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
