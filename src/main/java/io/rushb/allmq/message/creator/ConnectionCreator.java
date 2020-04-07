package io.rushb.allmq.message.creator;


import io.rushb.allmq.exception.CreateConnectionFailException;
import io.rushb.allmq.message.connection.Connection;
import io.rushb.allmq.message.message.Configuration;

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
    void setConfiguration(Configuration configuration);

    /**
     * create connection
     *
     * @return Connection
     * @throws CreateConnectionFailException e
     */
    Connection create() throws CreateConnectionFailException;
}
