package messagequeue.message.creator;


import messagequeue.message.message.MQ;

import java.util.HashMap;
import java.util.Map;

/**
 * lib
 *
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/5 22:14
 */
public class AllConnectionCreator {

    private final static Map<MQ, ConnectionCreator> LIB = new HashMap<>();

    static {
        try {
            boolean kafkaExist = null != Class.forName("org.apache.kafka.clients.producer.Producer");
            LIB.put(MQ.KAFKA, new KafkaConnectionCreator());
        } catch (ClassNotFoundException e) {
            // kafka not exists
        }

        try {
            boolean kafkaExist = null != Class.forName("org.apache.activemq.ActiveMQConnection");
            LIB.put(MQ.ACTIVEMQ, new ActivemqConnectionCreator());
        } catch (ClassNotFoundException e) {
            // ActiveMQ not exists
        }
    }

    public static ConnectionCreator getConnectionCreator(MQ mq) {
        ConnectionCreator connectionCreator = LIB.get(mq);
        if (connectionCreator == null) {
            throw new NullPointerException("找不到" + mq + "对应的connectionCreator");
        }
        return connectionCreator;
    }
}
