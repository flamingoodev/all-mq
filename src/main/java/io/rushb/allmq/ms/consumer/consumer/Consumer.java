package io.rushb.allmq.ms.consumer.consumer;

import io.rushb.allmq.ms.consumer.listener.MessageListener;

import java.io.Closeable;

/**
 * @author zxj<br>
 * 时间 2018/3/19 11:27
 * 说明 ...
 */
public interface Consumer extends Closeable {

    /**
     * set a listener
     * @param messageListener
     */
    void setMessageListener(MessageListener messageListener);



}
