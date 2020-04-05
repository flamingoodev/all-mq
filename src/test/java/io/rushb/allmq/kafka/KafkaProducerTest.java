package io.rushb.allmq.kafka;


import io.rushb.allmq.factory.ConnectionFactory;
import io.rushb.allmq.message.message.Configuration;
import io.rushb.allmq.message.message.MQ;
import io.rushb.allmq.message.connection.Connection;
import io.rushb.allmq.message.message.KeyValueMessage;
import io.rushb.allmq.message.producer.Producer;

/**
 * kafka生产者测试类
 *
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/5 22:14
 */
public class KafkaProducerTest {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.add("bootstrap.servers", "116.62.150.178:9092");
        configuration.add("acks", "all");
        configuration.add("retries", 0);
        configuration.add("batch.size", 16384);
        configuration.add("linger.ms", 1);
        configuration.add("buffer.memory", 33554432);
        configuration.add("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        configuration.add("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");


        ConnectionFactory connectionFactory = new ConnectionFactory(configuration, MQ.KAFKA);
        Connection connection = connectionFactory.getConnection();
        Producer producer = connection.createProducer("test");

        producer.sendMessage(new KeyValueMessage("TEST", "This is a test massage!"));
    }
}
