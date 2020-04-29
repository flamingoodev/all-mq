package messagequeue.modules.config.service;

import messagequeue.modules.config.entity.ConfigEntity;

/**
 * @author FLAMINGO
 * @since 2020/4/20 11:58:50
 */
public interface ConfigService {
    /**
     * 根据参数编码获取参数值
     *
     * @param code String
     * @return String
     */
    ConfigEntity queryByCode(String code);
}
