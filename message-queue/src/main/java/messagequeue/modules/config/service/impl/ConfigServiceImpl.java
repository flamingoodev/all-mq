package messagequeue.modules.config.service.impl;

import messagequeue.modules.config.dao.ConfigDao;
import messagequeue.modules.config.entity.ConfigEntity;
import messagequeue.modules.config.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author FLAMINGO
 * @since 2020/4/20 11:58:21
 */
@Service("configService")
public class ConfigServiceImpl implements ConfigService {
    @Autowired
    private ConfigDao configDao;

    @Override
    public ConfigEntity queryByCode(String code) {
        return configDao.queryByCode(code);
    }
}
