package io.rushb.allmq.template;


import io.rushb.allmq.MqTemplate;
import io.rushb.allmq.factory.ConnectionFactory;
import io.rushb.allmq.message.message.Configuration;
import io.rushb.allmq.message.message.MQ;
import io.rushb.allmq.message.message.Message;
import org.springframework.context.annotation.Bean;

/**
 * kafka消费者测试类
 *
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/5 22:14
 */
public class TemplateTest {


    public static void main(String[] args) {
        // TODO test
        MqTemplate.sendTest("test123", new Message("这是一个mqTemplate发出的消息"));
    }
    // TODO remove into message-app
    @Bean
    public ConnectionFactory connectionFactory() {
        Configuration configuration = new Configuration();
        configuration.add("mq", MQ.ACTIVEMQ);
        configuration.add("bootstrap.servers", "116.62.150.178:9092");
        configuration.add("group.id", "TEST_GROUP");
        configuration.add("enable.auto.commit", "true");
        configuration.add("auto.commit.interval.ms", "1000");
        configuration.add("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        configuration.add("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return new ConnectionFactory(configuration);
    }
}
