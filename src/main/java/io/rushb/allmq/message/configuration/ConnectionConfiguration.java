package io.rushb.allmq.message.configuration;

import com.sun.istack.internal.NotNull;
import io.rushb.allmq.message.message.Configuration;

/**
 * 配置接口
 *
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/6 00:25
 */
public class ConnectionConfiguration {
//    TODO 配置类待完成，需配合 MessageUtil类使用

    private Configuration configuration;

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(@NotNull Configuration configuration) {
        this.configuration = configuration;
    }
}
