package io.rushb.allmq.activemq;


import io.rushb.allmq.AllMsConnectionFactory;
import io.rushb.allmq.Configuration;
import io.rushb.allmq.MQ;
import io.rushb.allmq.ms.connection.Connection;
import io.rushb.allmq.ms.message.Message;
import io.rushb.allmq.ms.producer.Producer;

/**
 * @author zxj<br>
 * 时间 2018/3/19 18:01
 * 说明 ...
 */
public class ActivemqProducerTest {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.add("username","admin");
        configuration.add("password","admin");
        configuration.add("brokerURL","failover:(tcp://116.62.150.178:61616)");
        AllMsConnectionFactory allMsConnectionFactory = new AllMsConnectionFactory(configuration, MQ.ACTIVEMQ);

        Connection connection = allMsConnectionFactory.getConnection();
        Producer producer = connection.createProducer("test");
        producer.sendMessage(new Message("This is a test message!"));
        System.out.println("完成");
    }
}
