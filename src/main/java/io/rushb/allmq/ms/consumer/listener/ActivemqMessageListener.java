package io.rushb.allmq.ms.consumer.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author zxj<br>
 * 时间 2018/3/19 17:54
 * 说明 ...
 */
public class ActivemqMessageListener implements MessageListener {
    public final static Logger LOG = LoggerFactory.getLogger(ActivemqMessageListener.class);

    private io.rushb.allmq.ms.consumer.listener.MessageListener messageListener;

    public ActivemqMessageListener(io.rushb.allmq.ms.consumer.listener.MessageListener messageListener) {
        this.messageListener = messageListener;
    }

    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            try {
                messageListener.onMessage(new io.rushb.allmq.ms.message.Message(textMessage.getText()));
            } catch (JMSException e) {
                LOG.error("get message error");
                e.printStackTrace();
            }
        } else {
            LOG.error("not support message type : " + message.getClass().getName());
        }
    }


}
