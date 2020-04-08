package io.rushb.allmq.template;


import io.rushb.allmq.MqTemplate;
import io.rushb.allmq.factory.ConnectionFactory;
import io.rushb.allmq.message.connection.Connection;
import io.rushb.allmq.message.consumer.consumer.Consumer;
import io.rushb.allmq.message.consumer.listener.MessageListener;
import io.rushb.allmq.message.message.Configuration;
import io.rushb.allmq.message.message.MQ;
import io.rushb.allmq.message.message.Message;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * kafka消费者测试类
 *
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/5 22:14
 */
public class TemplateTest {

    @Test
    void contextLoads() {
    }

    @Test
    void kafkaProducerTest() {
        Configuration configuration = new Configuration();
        configuration.add("mq", MQ.KAFKA);
        configuration.add("bootstrap.servers", "localhost:9092");
        configuration.add("acks", "all");
        configuration.add("retries", 0);
        configuration.add("batch.size", 16384);
        configuration.add("linger.ms", 1);
        configuration.add("buffer.memory", 33554432);
        configuration.add("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        configuration.add("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        configuration.add("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        configuration.add("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        ConnectionFactory.build(configuration);
        MqTemplate.send("test", new Message("这是一条MqTemplate发出的消息"));
    }

    @Test
    void kafkaConsumerTest() throws IOException {
        Configuration configuration = new Configuration();
        configuration.add("mq", MQ.KAFKA);
        configuration.add("bootstrap.servers", "localhost:9092");
        configuration.add("group.id", "TEST_GROUP");
        configuration.add("enable.auto.commit", "true");
        configuration.add("auto.commit.interval.ms", "1000");
        configuration.add("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        configuration.add("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        configuration.add("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        configuration.add("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        ConnectionFactory.build(configuration);
        Connection connection = ConnectionFactory.getInstance().getConnection();
        Consumer consumer = connection.createConsumer("test");
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message msg) {
                System.out.println(msg);
            }
        });
        System.out.println(System.in.read());
    }

    @Test
    void activeMqProducerTest() {
        Configuration configuration = new Configuration();
        configuration.add("mq", MQ.ACTIVEMQ);
        configuration.add("username", "admin");
        configuration.add("password", "admin");
        configuration.add("brokerURL", "failover:(tcp://localhost:61616)");
        ConnectionFactory.build(configuration);
        MqTemplate.send("test", new Message("这是一个mqTemplate发出的消息2"));
    }

    @Test
    void activeMqConsumerTest() throws IOException {
        Configuration configuration = new Configuration();
        configuration.add("mq", MQ.ACTIVEMQ);
        configuration.add("username", "admin");
        configuration.add("password", "admin");
        configuration.add("brokerURL", "failover:(tcp://localhost:61616)");
        ConnectionFactory.build(configuration);
        Connection connection = ConnectionFactory.getInstance().getConnection();
        Consumer consumer = connection.createConsumer("test");
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message msg) {
                System.out.println(msg);
            }
        });
        System.out.println(System.in.read());
    }
}
