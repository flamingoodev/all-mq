package io.rushb.allmq;

import com.sun.istack.internal.NotNull;
import io.rushb.allmq.factory.ConnectionFactory;
import io.rushb.allmq.message.configuration.ConnectionConfiguration;
import io.rushb.allmq.message.connection.Connection;
import io.rushb.allmq.message.consumer.consumer.Consumer;
import io.rushb.allmq.message.consumer.consumer.KafkaConsumer;
import io.rushb.allmq.message.message.Configuration;
import io.rushb.allmq.message.message.Message;
import io.rushb.allmq.message.producer.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 消息队列
 *
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/6 23:37
 */
public class MqTemplate {
    public final static Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    /**
     * 消息
     */
    private static Message message;

    /**
     * 发送消息
     *
     * @param topic   topic
     * @param message message
     */
    public static void send(@NotNull String topic, @NotNull Message message) {
        ConnectionConfiguration connectionConfiguration = new ConnectionConfiguration();
        Configuration configuration = connectionConfiguration.getConfiguration();
        if (configuration != null) {
            ConnectionFactory connectionFactory = new ConnectionFactory(configuration);
            Connection connection = connectionFactory.getConnection();
            Producer producer = connection.createProducer(topic);
            producer.sendMessage(message);
            logger.info("Message send success, " + "topic: " + topic + ", message: " + message);
        }
    }

    /**
     * 发送消息
     *
     * @param topic   topic
     * @param message message
     */
    public static void sendTest(@NotNull String topic, @NotNull Message message) {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        Producer producer = connection.createProducer(topic);
        producer.sendMessage(message);
        logger.info("Message send success, " + "topic: " + topic + ", message: " + message);
    }

    /**
     * 监听消息
     *
     * @param topic topic
     * @return message
     */
    public static Message listen(@NotNull String topic) {
        ConnectionConfiguration connectionConfiguration = new ConnectionConfiguration();
        Configuration configuration = connectionConfiguration.getConfiguration();
        if (configuration != null) {
            ConnectionFactory connectionFactory = new ConnectionFactory(configuration);
            Connection connection = connectionFactory.getConnection();
            Consumer consumer = connection.createConsumer(topic);
            consumer.setMessageListener(message -> MqTemplate.message = message);
        }
        return message;
    }
}
