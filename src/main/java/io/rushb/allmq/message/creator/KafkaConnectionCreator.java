package io.rushb.allmq.message.creator;


import io.rushb.allmq.message.message.Configuration;
import io.rushb.allmq.message.connection.Connection;
import io.rushb.allmq.message.connection.KafkaConnection;
import io.rushb.allmq.util.Asserts;

/**
 * kafka连接创建
 *
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/5 22:14
 */
public class KafkaConnectionCreator implements ConnectionCreator {
    private Configuration configuration;


    @Override
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Connection create() {
        Asserts.notNull(configuration, "configuration is not set");
        return new KafkaConnection(this.configuration);
    }
}
