package io.rushb.allmq.message.producer;


import io.rushb.allmq.message.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.io.IOException;

/**
 * ActiveMQ生产者
 *
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/5 22:14
 */
public class ActivemqProducer implements Producer {
    public final static Logger logger = LoggerFactory.getLogger(ActivemqProducer.class);
    private MessageProducer producer;
    private Session session;

    public ActivemqProducer(MessageProducer producer, Session session) {
        this.producer = producer;
        this.session = session;
    }

    @Override
    public void sendMessage(Message message) {
        try {
            TextMessage textMessage = session.createTextMessage(message.getData());
            producer.send(textMessage);
        } catch (JMSException e) {
            logger.error("send activemq message fail , " + e.getMessage());
        }
    }

    @Override
    public void close() throws IOException {
        if (producer != null) {
            try {
                producer.close();
            } catch (JMSException e) {
                e.printStackTrace();
            } finally {
                producer = null;
            }
        }
    }
}
