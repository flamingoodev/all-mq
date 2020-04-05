package io.rushb.allmq.util;



import io.rushb.allmq.Configuration;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * @author zxj<br>
 * 时间 2018/3/19 15:17
 * 说明 ...
 */
public class PropertiesUtil {

    public static Properties convert(Configuration configuration){
        Properties properties = new Properties();
        Iterator<Map.Entry<String, Object>> iterator = configuration.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Object> entry = iterator.next();
            properties.put(entry.getKey(),entry.getValue());
        }
        return properties;
    }
}
