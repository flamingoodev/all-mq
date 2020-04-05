package io.rushb.allmq;


import com.sun.istack.internal.NotNull;
import io.rushb.allmq.exception.NotSupportParamException;
import io.rushb.allmq.ms.connection.Connection;
import io.rushb.allmq.ms.creator.ConnectionCreator;
import io.rushb.allmq.util.Asserts;

/**
 * @author zxj<br>
 * 时间 2018/3/19 11:16
 * 说明 ...
 */
public class AllMsConnectionFactory {
    public static final String MQ_NAME = "mq";
    private Configuration configuration;
    private MQ mq;

    public AllMsConnectionFactory(@NotNull Configuration configuration) {
        Asserts.notNull(configuration, "configuration can not be null");
        Object omq = configuration.get(MQ_NAME);
        if (omq == null) {
            throw new NotSupportParamException("you should set 'mq' to the configuration on this constructor , or use an other constructor ");
        }
        String mq = String.valueOf(omq);
        if ("kafka".equalsIgnoreCase(mq)) {
            this.mq = MQ.KAFKA;
        } else if ("activemq".equalsIgnoreCase(mq)) {
            this.mq = MQ.ACTIVEMQ;
        } else {
            throw new NotSupportParamException("the mq config can not be " + mq);
        }

        this.configuration = configuration;
    }

    public AllMsConnectionFactory(Configuration configuration, MQ mq) {
        Asserts.notNull(configuration, "configuration can not be null");
        Asserts.notNull(mq, "mq can not be null");
        this.configuration = configuration;
        this.mq = mq;
    }


    public Connection getConnection() {
        ConnectionCreator connectionCreator = AllMsLib.getConnectionCreator(mq);
        connectionCreator.setConfiguration(configuration);
        Connection connection = connectionCreator.create();
        return connection;
    }
}
