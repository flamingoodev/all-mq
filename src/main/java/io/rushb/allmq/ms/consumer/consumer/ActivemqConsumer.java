package io.rushb.allmq.ms.consumer.consumer;

import io.rushb.allmq.ms.consumer.listener.ActivemqMessageListener;
import io.rushb.allmq.ms.consumer.listener.MessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import java.io.IOException;

/**
 * @author zxj<br>
 * 时间 2018/3/19 17:45
 * 说明 ...
 */
public class ActivemqConsumer implements Consumer {
    public final static Logger LOG = LoggerFactory.getLogger(ActivemqConsumer.class);
    private MessageConsumer consumer;


    public ActivemqConsumer(MessageConsumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public void setMessageListener(MessageListener messageListener) {
        try {
            this.consumer.setMessageListener(new ActivemqMessageListener(messageListener));
        } catch (JMSException e) {
            LOG.error("set message listener fail", e);
        }
    }

    @Override
    public void close() throws IOException {
        if(consumer != null){
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
