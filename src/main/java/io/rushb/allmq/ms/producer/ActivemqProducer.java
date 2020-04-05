package io.rushb.allmq.ms.producer;


import io.rushb.allmq.ms.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.io.IOException;

/**
 * @author zxj<br>
 * 时间 2018/3/19 17:48
 * 说明 ...
 */
public class ActivemqProducer implements Producer {
    public final static Logger LOG = LoggerFactory.getLogger(ActivemqProducer.class);
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
            LOG.error("send activemq message fail , " + e.getMessage());
        }
    }

    @Override
    public void close() throws IOException {
        if(producer != null){
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
