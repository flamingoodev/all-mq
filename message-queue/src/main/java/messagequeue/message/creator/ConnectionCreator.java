package messagequeue.message.creator;


import messagequeue.exception.CreateConnectionFailException;
import messagequeue.message.connection.Connection;
import messagequeue.message.message.MqConfiguration;

/**
 * 连接创建类
 *
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/5 22:14
 */
public interface ConnectionCreator {

    /**
     * set configuration
     *
     * @param configuration configuration
     */
    void setConfiguration(MqConfiguration configuration);

    /**
     * create connection
     *
     * @return Connection
     * @throws CreateConnectionFailException e
     */
    Connection create() throws CreateConnectionFailException;
}
