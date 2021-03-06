package messagequeue.message.consumer.listener;


import messagequeue.message.message.Message;

/**
 * 消息监听类
 *
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/5 22:14
 */
public interface MessageListener {
    /**
     * onMessage
     *
     * @param message message
     */
    void onMessage(Message message);
}
