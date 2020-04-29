package messagequeue.message.connection;

import messagequeue.exception.CreateConnectionFailException;
import messagequeue.exception.NotSupportParamException;
import messagequeue.message.consumer.consumer.ActivemqConsumer;
import messagequeue.message.consumer.consumer.Consumer;
import messagequeue.message.message.MqConfiguration;
import messagequeue.message.producer.ActivemqProducer;
import messagequeue.message.producer.Producer;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;

import javax.jms.*;

/**
 * ActiveMQ连接
 *
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/5 22:14
 */
public class ActivemqConnection implements Connection {

    public static final String TRANSACTION_NAME = "transaction";

    public static final String AC_KNOWLEDGE_MODE_NAME = "acknowledgeMode";

    public static final String TYPE_NAME = "type";

    private MqConfiguration configuration;

    private javax.jms.Connection connection;

    private Session session;

    /**
     * 0 消息列队
     * 1 主题
     */
    private int type = 0;

    public ActivemqConnection(MqConfiguration configuration, javax.jms.Connection connection) {
        this.configuration = configuration;
        this.connection = connection;
        init();
    }

    private void init() {
        // transaction
        boolean transaction = false;
        Object transactionName = configuration.get(TRANSACTION_NAME);
        if (transactionName != null) {
            if (Boolean.toString(true).equals(transactionName)) {
                transaction = true;
            } else if (Boolean.toString(false).equals(transactionName)) {
                transaction = false;
            } else {
                throw new NotSupportParamException("the value of " + TRANSACTION_NAME + " can not be " + transactionName);
            }
        }
        // auto mode
        int mode = 1;
        Object o = configuration.get(AC_KNOWLEDGE_MODE_NAME);
        if (o != null) {
            o = String.valueOf(o);
            try {
                mode = Integer.parseInt(String.valueOf(o));
            } catch (NumberFormatException e) {
                throw new NumberFormatException("the " + AC_KNOWLEDGE_MODE_NAME + " config just only support number");
            }
        } else {
            mode = 1;
        }
        // queue or topic
        Object to = configuration.get(TYPE_NAME);
        if (to != null) {
            this.type = Integer.parseInt(String.valueOf(to));
            if (this.type != 0 && this.type != 1) {
                throw new NotSupportParamException("the value of " + TYPE_NAME + " can not be " + to);
            }
        } else {
            this.type = 0;
        }
        try {
            this.session = this.connection.createSession(transaction, mode);
        } catch (JMSException e) {
            throw new CreateConnectionFailException("create session fail", e);
        }
    }

    @Override
    public Consumer createConsumer(String topicName) throws CreateConnectionFailException {
        Destination destination = createDestination(topicName);
        try {
            MessageConsumer consumer = session.createConsumer(destination);
            return new ActivemqConsumer(consumer);
        } catch (JMSException e) {
            throw new CreateConnectionFailException("create activemq consumer fail", e);
        }
    }

    @Override
    public Producer createProducer(String topicName) throws CreateConnectionFailException {
        Destination destination = createDestination(topicName);
        try {
            MessageProducer producer = session.createProducer(destination);
            return new ActivemqProducer(producer, session);
        } catch (JMSException e) {
            throw new CreateConnectionFailException("create activemq producer fail", e);
        }
    }

    protected Destination createDestination(String topic) {
        if (type == 0) {
            return new ActiveMQQueue(topic);
        } else {
            return new ActiveMQTopic(topic);
        }
    }
}
