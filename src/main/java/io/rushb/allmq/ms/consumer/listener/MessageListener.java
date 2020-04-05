package io.rushb.allmq.ms.consumer.listener;


import io.rushb.allmq.ms.message.Message;

/**
 * @author zxj<br>
 * 时间 2018/3/19 14:52
 * 说明 ...
 */
public interface MessageListener {


    void onMessage(Message message);


}
