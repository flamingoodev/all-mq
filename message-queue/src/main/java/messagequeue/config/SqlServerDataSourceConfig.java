package messagequeue.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 */
public class SqlServerDataSourceConfig {

    @Bean("dataSourceMq")
    public DataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        druidDataSource.setUrl("jdbc:sqlserver://localhost:1433;DatabaseName=sso_master");
        druidDataSource.setUsername("sa");
        druidDataSource.setPassword("oneplus@2018");
        return druidDataSource;
    }

    @Bean("sqlSessionFactoryMq")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSourceMq") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        org.springframework.core.io.Resource[] resources = new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/sqlserver/**/*.xml");
        sqlSessionFactoryBean.setMapperLocations(resources);
        // mybatis plus config
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setBanner(false);
        sqlSessionFactoryBean.setGlobalConfig(globalConfig);
        return sqlSessionFactoryBean.getObject();
    }
}