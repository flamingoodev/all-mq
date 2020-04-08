package io.rushb.allmq.message.consumer.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * ActiveMQ消息监听
 *
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/5 22:14
 */
public class ActivemqMessageListener implements MessageListener {

    public final static Logger logger = LoggerFactory.getLogger(ActivemqMessageListener.class);

    private io.rushb.allmq.message.consumer.listener.MessageListener messageListener;

    public ActivemqMessageListener(io.rushb.allmq.message.consumer.listener.MessageListener messageListener) {
        this.messageListener = messageListener;
    }

    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            try {
                messageListener.onMessage(new io.rushb.allmq.message.message.Message(textMessage.getText()));
            } catch (JMSException e) {
                logger.error("Get message error");
                e.printStackTrace();
            }
        } else {
            logger.error("Not support message type : " + message.getClass().getName());
        }
    }
}
