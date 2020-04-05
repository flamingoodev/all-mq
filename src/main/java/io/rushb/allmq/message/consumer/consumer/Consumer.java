package io.rushb.allmq.message.consumer.consumer;

import io.rushb.allmq.message.consumer.listener.MessageListener;

import java.io.Closeable;

/**
 * 消费者
 *
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/5 22:14
 */
public interface Consumer extends Closeable {

    /**
     * set a listener
     *
     * @param messageListener
     */
    void setMessageListener(MessageListener messageListener);


}
