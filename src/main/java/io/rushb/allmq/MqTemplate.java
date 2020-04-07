package io.rushb.allmq;

import io.rushb.allmq.factory.ConnectionFactory;
import io.rushb.allmq.message.connection.Connection;
import io.rushb.allmq.message.consumer.consumer.Consumer;
import io.rushb.allmq.message.consumer.consumer.KafkaConsumer;
import io.rushb.allmq.message.consumer.listener.MessageListener;
import io.rushb.allmq.message.message.Message;
import io.rushb.allmq.message.producer.Producer;
import io.rushb.allmq.util.Asserts;
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
    public static void send(String topic, Message message) {
        Asserts.notNull(topic, "topic can not be null");
        Asserts.notNull(message, "message can not be null");
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
    public static Message listen(String topic) {
        Asserts.notNull(topic, "topic can not be null");
        message = null;
        Connection connection = ConnectionFactory.getInstance().getConnection();
        Consumer consumer = connection.createConsumer(topic);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message msg) {
                message = msg;
            }
        });
        return message;
    }
}
