package io.rushb.allmq.kafka;


import io.rushb.allmq.factory.ConnectionFactory;
import io.rushb.allmq.message.message.Configuration;
import io.rushb.allmq.message.message.MQ;
import io.rushb.allmq.message.connection.Connection;
import io.rushb.allmq.message.consumer.consumer.Consumer;
import io.rushb.allmq.message.consumer.listener.MessageListener;
import io.rushb.allmq.message.message.Message;

/**
 * kafka消费者测试类
 *
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/5 22:14
 */
public class KafkaConsumerTest {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.add("bootstrap.servers", "116.62.150.178:9092");
        configuration.add("group.id", "TEST_GROUP");
        configuration.add("enable.auto.commit", "true");
        configuration.add("auto.commit.interval.ms", "1000");
        configuration.add("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        configuration.add("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        ConnectionFactory connectionFactory = new ConnectionFactory(configuration, MQ.KAFKA);
        Connection connection = connectionFactory.getConnection();
        Consumer consumer = connection.createConsumer("test");
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                System.out.println(message);
            }
        });
    }
}
