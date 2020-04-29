package messagequeue.factory;

import messagequeue.constants.Constant;
import messagequeue.exception.NotSupportParamException;
import messagequeue.message.connection.Connection;
import messagequeue.message.creator.AllConnectionCreator;
import messagequeue.message.creator.ConnectionCreator;
import messagequeue.message.message.MqConfiguration;
import messagequeue.message.message.MQ;
import messagequeue.util.Asserts;

/**
 * 连接工厂类
 *
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/5 22:14
 */
public class ConnectionFactory {

    private volatile static ConnectionFactory instance;

    public static final String MQ_NAME = "mq";

    private static MqConfiguration configuration;

    private MQ mq;

    private ConnectionFactory() {
    }

    private ConnectionFactory(MqConfiguration config) {
        Asserts.notNull(config, "configuration not be null , you should call [ConnectionFactory.build()] first");
        Object omq = config.get(MQ_NAME);
        Asserts.notNull(omq, "mq can not be null");
        String mq = String.valueOf(omq);
        if (Constant.KAFKA.equalsIgnoreCase(mq)) {
            this.mq = MQ.KAFKA;
        } else if (Constant.ACTIVE_MQ.equalsIgnoreCase(mq)) {
            this.mq = MQ.ACTIVEMQ;
        } else {
            throw new NotSupportParamException("the mq config can not be " + mq);
        }
        configuration = config;
        new ConnectionFactory();
    }

    public static void build(MqConfiguration configuration) {
        if (instance == null) {
            synchronized (ConnectionFactory.class) {
                if (instance == null) {
                    instance = new ConnectionFactory(configuration);
                }
            }
        }
    }

    public static ConnectionFactory getInstance() {
        if (instance == null) {
            synchronized (ConnectionFactory.class) {
                if (instance == null) {
                    instance = new ConnectionFactory(configuration);
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        ConnectionCreator connectionCreator = AllConnectionCreator.getConnectionCreator(mq);
        connectionCreator.setConfiguration(configuration);
        return connectionCreator.create();
    }
}
