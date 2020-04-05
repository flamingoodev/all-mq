package io.rushb.allmq.ms.producer;



import io.rushb.allmq.ms.message.Message;

import java.io.Closeable;

/**
 * @author zxj<br>
 * 时间 2018/3/19 11:27
 * 说明 ...
 */
public interface Producer extends Closeable{

    void sendMessage(Message message);
}
