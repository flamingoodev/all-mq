package io.rushb.allmq.message.creator;


import io.rushb.allmq.message.message.Configuration;
import io.rushb.allmq.exception.CreateConnectionFailException;
import io.rushb.allmq.message.connection.Connection;

/**
 * 连接创建类
 *
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/5 22:14
 */
public interface ConnectionCreator {

    void setConfiguration(Configuration configuration);

    Connection create() throws CreateConnectionFailException;
}
