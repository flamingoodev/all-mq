package messagequeue.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 */
public class MySqlDataSourceConfig {

    @Bean("dataSourceMq")
    @ConfigurationProperties(prefix = "spring.datasource.druid.mq")
    public DataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/all_mq?allowMultiQueries=true&serverTimezone=GMT%2B8&characterEncoding=UTF-8");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("root");
        return druidDataSource;
    }

    @Bean("sqlSessionFactoryMq")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSourceMq") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        org.springframework.core.io.Resource[] resources = new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/mysql/**/*.xml");
        sqlSessionFactoryBean.setMapperLocations(resources);
        // mybatis plus config
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setBanner(false);
        sqlSessionFactoryBean.setGlobalConfig(globalConfig);
        return sqlSessionFactoryBean.getObject();
    }
}