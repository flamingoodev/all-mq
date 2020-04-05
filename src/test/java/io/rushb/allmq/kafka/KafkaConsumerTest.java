package io.rushb.allmq.kafka;


import io.rushb.allmq.AllMsConnectionFactory;
import io.rushb.allmq.Configuration;
import io.rushb.allmq.MQ;
import io.rushb.allmq.ms.connection.Connection;
import io.rushb.allmq.ms.consumer.consumer.Consumer;
import io.rushb.allmq.ms.consumer.listener.MessageListener;
import io.rushb.allmq.ms.message.Message;

/**
 * @author zxj<br>
 * 时间 2018/3/19 16:25
 * 说明 ...
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

        AllMsConnectionFactory allMsConnectionFactory = new AllMsConnectionFactory(configuration, MQ.KAFKA);
        Connection connection = allMsConnectionFactory.getConnection();
        Consumer consumer = connection.createConsumer("test");
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                System.out.println(message);
            }
        });
    }
}
