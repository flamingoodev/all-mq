package io.rushb.allmq.activemq;


import io.rushb.allmq.factory.ConnectionFactory;
import io.rushb.allmq.message.message.Configuration;
import io.rushb.allmq.message.message.MQ;
import io.rushb.allmq.message.connection.Connection;
import io.rushb.allmq.message.consumer.consumer.Consumer;
import io.rushb.allmq.message.consumer.listener.MessageListener;
import io.rushb.allmq.message.message.Message;

/**
 * ActiveMQ消费者测试类
 *
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/5 22:14
 */
public class ActivemqConsumerTest {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.add("username", "admin");
        configuration.add("password", "admin");
        configuration.add("brokerURL", "failover:(tcp://116.62.150.178:61616)");
        ConnectionFactory connectionFactory = new ConnectionFactory(configuration, MQ.ACTIVEMQ);

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
