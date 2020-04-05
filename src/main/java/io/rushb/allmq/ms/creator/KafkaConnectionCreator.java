package io.rushb.allmq.ms.creator;


import io.rushb.allmq.Configuration;
import io.rushb.allmq.ms.connection.Connection;
import io.rushb.allmq.ms.connection.KafkaConnection;
import io.rushb.allmq.util.Asserts;

/**
 * @author zxj<br>
 * 时间 2018/3/19 11:39
 * 说明 创建kafka的connection
 */
public class KafkaConnectionCreator implements ConnectionCreator {
    private Configuration configuration;


    @Override
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Connection create() {
        Asserts.notNull(configuration,"configuration is not set");
        return new KafkaConnection(this.configuration);
    }
}
