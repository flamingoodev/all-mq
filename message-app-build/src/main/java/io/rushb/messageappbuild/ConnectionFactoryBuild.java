package io.rushb.messageappbuild;

import messagequeue.factory.ConnectionFactory;
import messagequeue.message.message.MQ;
import messagequeue.message.message.MqConfiguration;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/28 14:27
 */
@Service
public class ConnectionFactoryBuild {
    @PostConstruct
    private void init() {
        MqConfiguration configuration = new MqConfiguration();
        configuration.add("mq", MQ.ACTIVEMQ);
        configuration.add("username", "admin");
        configuration.add("password", "admin");
        configuration.add("brokerURL", "failover:(tcp://localhost:61616)");
        ConnectionFactory.build(configuration);
    }

//    @PostConstruct
    private void init1() {
        MqConfiguration configuration = new MqConfiguration();
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
    }
}
