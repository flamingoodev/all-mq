package io.rushb.allmq.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zxj<br>
 * 时间 2018/3/19 11:57
 * 说明 ...
 */
public class Asserts {
    public final static Logger LOG = LoggerFactory.getLogger(Asserts.class);

    public static void notNull(Object object,String message){
        if(object == null){
            throw new NullPointerException(message);
        }

    }
}
