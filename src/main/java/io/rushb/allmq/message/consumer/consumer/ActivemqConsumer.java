package io.rushb.allmq.message.consumer.consumer;

import io.rushb.allmq.message.consumer.listener.ActivemqMessageListener;
import io.rushb.allmq.message.consumer.listener.MessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import java.io.IOException;

/**
 * ActiveMQ消费者
 *
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/5 22:14
 */
public class ActivemqConsumer implements Consumer {

    public final static Logger logger = LoggerFactory.getLogger(ActivemqConsumer.class);

    private MessageConsumer consumer;

    public ActivemqConsumer(MessageConsumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public void setMessageListener(MessageListener messageListener) {
        try {
            this.consumer.setMessageListener(new ActivemqMessageListener(messageListener));
        } catch (JMSException e) {
            logger.error("set message listener fail", e);
        }
    }

    @Override
    public void close() throws IOException {
        if (consumer != null) {
            try {
                consumer.close();
            } catch (JMSException e) {
                e.printStackTrace();
            } finally {
                consumer = null;
            }
        }
    }
}
