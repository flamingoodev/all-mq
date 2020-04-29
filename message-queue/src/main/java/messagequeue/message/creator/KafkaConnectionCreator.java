package messagequeue.message.creator;


import messagequeue.message.connection.Connection;
import messagequeue.message.connection.KafkaConnection;
import messagequeue.message.message.MqConfiguration;
import messagequeue.util.Asserts;

/**
 * kafka连接创建
 *
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/5 22:14
 */
public class KafkaConnectionCreator implements ConnectionCreator {

    private MqConfiguration configuration;

    @Override
    public void setConfiguration(MqConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Connection create() {
        Asserts.notNull(configuration, "Configuration is not set");
        return new KafkaConnection(this.configuration);
    }
}
