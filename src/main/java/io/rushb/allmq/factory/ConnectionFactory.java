package io.rushb.allmq.factory;

import io.rushb.allmq.constants.Constant;
import io.rushb.allmq.exception.NotSupportParamException;
import io.rushb.allmq.message.connection.Connection;
import io.rushb.allmq.message.creator.AllConnectionCreator;
import io.rushb.allmq.message.creator.ConnectionCreator;
import io.rushb.allmq.message.message.Configuration;
import io.rushb.allmq.message.message.MQ;
import io.rushb.allmq.util.Asserts;

/**
 * 连接工厂类
 *
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/5 22:14
 */
public class ConnectionFactory {
    private volatile static ConnectionFactory instance;
    public static final String MQ_NAME = "mq";
    private static Configuration configuration;
    private MQ mq;

    private ConnectionFactory() {
    }

    private ConnectionFactory(Configuration config) {
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

    public static void build(Configuration configuration) {
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
