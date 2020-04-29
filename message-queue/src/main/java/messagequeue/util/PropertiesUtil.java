package messagequeue.util;


import messagequeue.message.message.MqConfiguration;

import java.util.Map;
import java.util.Properties;

/**
 * 配置工具类
 *
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/5 22:14
 */
public class PropertiesUtil {

    public static Properties convert(MqConfiguration configuration) {
        Properties properties = new Properties();
        for (Map.Entry<String, Object> entry : configuration.entrySet()) {
            properties.put(entry.getKey(), entry.getValue());
        }
        return properties;
    }
}
