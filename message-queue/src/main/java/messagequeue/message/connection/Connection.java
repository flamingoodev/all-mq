package messagequeue.message.connection;


import messagequeue.exception.CreateConnectionFailException;
import messagequeue.message.consumer.consumer.Consumer;
import messagequeue.message.producer.Producer;

/**
 * 连接类
 *
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/5 22:14
 */
public interface Connection {
    /**
     * 创建consumer
     *
     * @param topic topic
     * @return Consumer
     * @throws CreateConnectionFailException e
     */
    Consumer createConsumer(String topic) throws CreateConnectionFailException;

    /**
     * Producer
     *
     * @param topic topic
     * @return Producer
     * @throws CreateConnectionFailException e
     */
    Producer createProducer(String topic) throws CreateConnectionFailException;
}
