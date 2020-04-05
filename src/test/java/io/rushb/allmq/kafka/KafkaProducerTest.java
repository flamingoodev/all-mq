package io.rushb.allmq.kafka;


import io.rushb.allmq.AllMsConnectionFactory;
import io.rushb.allmq.Configuration;
import io.rushb.allmq.MQ;
import io.rushb.allmq.ms.connection.Connection;
import io.rushb.allmq.ms.message.KeyValueMessage;
import io.rushb.allmq.ms.producer.Producer;

/**
 * @author zxj<br>
 * 时间 2018/3/19 16:11
 * 说明 ...
 */
public class KafkaProducerTest {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.add("bootstrap.servers","116.62.150.178:9092");
        configuration.add("acks","all");
        configuration.add("retries",0);
        configuration.add("batch.size", 16384);
        configuration.add("linger.ms", 1);
        configuration.add("buffer.memory", 33554432);
        configuration.add("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        configuration.add("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");


        AllMsConnectionFactory allMsConnectionFactory = new AllMsConnectionFactory(configuration, MQ.KAFKA);
        Connection connection = allMsConnectionFactory.getConnection();
        Producer producer = connection.createProducer("test");

        producer.sendMessage(new KeyValueMessage("TEST", "This is a test massage!"));
    }
}
