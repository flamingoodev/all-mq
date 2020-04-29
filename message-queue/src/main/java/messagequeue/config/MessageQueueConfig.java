package messagequeue.config;

import lombok.AllArgsConstructor;
import messagequeue.constants.Constant;
import messagequeue.exception.ConnectionConfigErrorException;
import messagequeue.factory.ConnectionFactory;
import messagequeue.message.message.MQ;
import messagequeue.message.message.MqConfiguration;
import messagequeue.modules.config.entity.ConfigEntity;
import messagequeue.modules.config.service.ConfigService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;

/**
 * 连接配置类
 *
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 */
@Configuration
@AllArgsConstructor
@ComponentScans({@ComponentScan("messagequeue")})
@MapperScan(basePackages = "messagequeue.modules.config.dao", sqlSessionFactoryRef = "sqlSessionFactoryMq")
public class MessageQueueConfig {
    private final ConfigService configService;

    @PostConstruct
    private void init() {
        ConfigEntity configEntity = configService.queryByCode(Constant.MQ_CONFIG_CODE);
        if (configEntity == null) {
            throw new ConnectionConfigErrorException("No MQ connection config set, please check config code: " + Constant.MQ_CONFIG_CODE);
        }
        ConnectionFactory.build(getConfig(configEntity));
    }

    private MqConfiguration getConfig(ConfigEntity config) {
        if (Constant.ACTIVE_MQ.equalsIgnoreCase(config.getArguments1())) {
            return getActiveMqConfig(config);
        } else if (Constant.KAFKA.equalsIgnoreCase(config.getArguments1())) {
            return getKafkaConfig(config);
        } else {
            throw new ConnectionConfigErrorException("The MQ connection config error, please check config name: " + config.getArguments1());
        }
    }

    /**
     * 获取kafka配置
     *
     * @param config config
     * @return config
     */
    private MqConfiguration getActiveMqConfig(ConfigEntity config) {
        MqConfiguration configuration = new MqConfiguration();
        configuration.add("mq", MQ.ACTIVEMQ);
        configuration.add("username", config.getUsername());
        configuration.add("password", config.getPassword());
        configuration.add("brokerURL", "failover:(" + assembleIp(config) + ")");
        return configuration;
    }

    /**
     * 获取kafka配置
     *
     * @param config config
     * @return config
     */
    private MqConfiguration getKafkaConfig(ConfigEntity config) {
        MqConfiguration configuration = new MqConfiguration();
        configuration.add("mq", MQ.KAFKA);
        configuration.add("bootstrap.servers", assembleIp(config));
        configuration.add("group.id", "TEST_GROUP");
        configuration.add("enable.auto.commit", "true");
        configuration.add("auto.commit.interval.ms", "1000");
        configuration.add("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        configuration.add("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        configuration.add("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        configuration.add("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return configuration;
    }

    /**
     * 组装IP
     *
     * @param config config
     * @return ip
     */
    private String assembleIp(ConfigEntity config) {
        String ip = config.getProtocol() + "://" + config.getDomain() + ":" + config.getPort();
        if (!StringUtils.isEmpty(config.getArguments2())) {
            ip += "," + config.getArguments2();
        }
        return ip;
    }
}