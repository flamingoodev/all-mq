package io.rushb.allmq.message.producer;


import io.rushb.allmq.message.message.Message;

import java.io.Closeable;

/**
 * 生产者
 *
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/5 22:14
 */
public interface Producer extends Closeable {

    void sendMessage(Message message);
}
