package messagequeue.modules.config.dao;

import messagequeue.modules.config.entity.ConfigEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface ConfigDao {
    /**
     * 根据参数编码获取参数值
     *
     * @param code String
     * @return String
     */
    ConfigEntity queryByCode(@Param("code") String code);
}
