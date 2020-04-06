package io.rushb.allmq.factory;


import io.rushb.allmq.message.creator.AllConnectionCreator;
import io.rushb.allmq.message.message.Configuration;
import io.rushb.allmq.message.message.MQ;
import io.rushb.allmq.constants.Constant;
import io.rushb.allmq.exception.NotSupportParamException;
import io.rushb.allmq.message.connection.Connection;
import io.rushb.allmq.message.creator.ConnectionCreator;
import io.rushb.allmq.util.Asserts;

/**
 * 连接工厂类
 *
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/5 22:14
 */
public class ConnectionFactory {
    private static ConnectionFactory instance;
    public static final String MQ_NAME = "mq";
    private Configuration configuration;
    private MQ mq;

    private ConnectionFactory() {
    }

    public ConnectionFactory(Configuration configuration) {
        Asserts.notNull(configuration, "configuration can not be null");
        Object omq = configuration.get(MQ_NAME);
        if (omq == null) {
            throw new NotSupportParamException("you should set 'mq' to the configuration on this constructor , or use an other constructor ");
        }
        String mq = String.valueOf(omq);
        if (Constant.KAFKA.equalsIgnoreCase(mq)) {
            this.mq = MQ.KAFKA;
        } else if (Constant.ACTIVE_MQ.equalsIgnoreCase(mq)) {
            this.mq = MQ.ACTIVEMQ;
        } else {
            throw new NotSupportParamException("the mq config can not be " + mq);
        }

        this.configuration = configuration;
    }

    public ConnectionFactory(Configuration configuration, MQ mq) {
        Asserts.notNull(configuration, "configuration can not be null");
        Asserts.notNull(mq, "mq can not be null");
        this.configuration = configuration;
        this.mq = mq;
    }

    public static ConnectionFactory getInstance() {
        if (instance == null) {
            instance = new ConnectionFactory();
        }
        return instance;
    }

    public Connection getConnection() {
        ConnectionCreator connectionCreator = AllConnectionCreator.getConnectionCreator(mq);
        connectionCreator.setConfiguration(configuration);
        return connectionCreator.create();
    }
}
