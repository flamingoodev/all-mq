package messagequeue;

import messagequeue.factory.ConnectionFactory;
import messagequeue.message.connection.Connection;
import messagequeue.message.message.Message;
import messagequeue.message.producer.Producer;
import messagequeue.util.Asserts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 消息队列
 *
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/6 23:37
 */
public class MqTemplate {

    public final static Logger logger = LoggerFactory.getLogger(MqTemplate.class);

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
}
