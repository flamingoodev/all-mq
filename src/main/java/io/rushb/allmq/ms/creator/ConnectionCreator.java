package io.rushb.allmq.ms.creator;


import io.rushb.allmq.Configuration;
import io.rushb.allmq.exception.CreateConnectionFailException;
import io.rushb.allmq.ms.connection.Connection;

/**
 * @author zxj<br>
 * 时间 2018/3/19 11:30
 * 说明 ...
 */
public interface ConnectionCreator {

    void setConfiguration(Configuration configuration);

    Connection create() throws CreateConnectionFailException;
}
