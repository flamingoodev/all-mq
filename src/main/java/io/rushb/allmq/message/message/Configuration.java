package io.rushb.allmq.message.message;

import java.util.HashMap;

/**
 * 配置类
 *
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/5 22:14
 */
public class Configuration extends HashMap<String, Object> {

    private static final long serialVersionUID = -7171471395590154318L;

    public void add(String key, Object object) {
        this.put(key, object);
    }
}
