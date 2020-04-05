package io.rushb.allmq.activemq;


import io.rushb.allmq.AllMsConnectionFactory;
import io.rushb.allmq.Configuration;
import io.rushb.allmq.MQ;
import io.rushb.allmq.ms.connection.Connection;
import io.rushb.allmq.ms.consumer.consumer.Consumer;
import io.rushb.allmq.ms.consumer.listener.MessageListener;
import io.rushb.allmq.ms.message.Message;

/**
 * @author zxj<br>
 * 时间 2018/3/19 18:04
 * 说明 ...
 */
public class ActivemqConsumerTest {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.add("username","admin");
        configuration.add("password","admin");
        configuration.add("brokerURL","failover:(tcp://116.62.150.178:61616)");
        AllMsConnectionFactory allMsConnectionFactory = new AllMsConnectionFactory(configuration, MQ.ACTIVEMQ);

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
