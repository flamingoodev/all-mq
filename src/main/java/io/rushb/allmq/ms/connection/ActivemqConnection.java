package io.rushb.allmq.ms.connection;

import io.rushb.allmq.Configuration;
import io.rushb.allmq.exception.CreateConnectionFailException;
import io.rushb.allmq.exception.NotSupportParamException;
import io.rushb.allmq.ms.consumer.consumer.ActivemqConsumer;
import io.rushb.allmq.ms.consumer.consumer.Consumer;
import io.rushb.allmq.ms.producer.ActivemqProducer;
import io.rushb.allmq.ms.producer.Producer;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;

import javax.jms.*;

/**
 * @author zxj<br>
 * 时间 2018/3/19 17:15
 * 说明 ...
 */
public class ActivemqConnection implements Connection {
    public static final String TRANSACTION_NAME = "transaction";
    public static final String acknowledgeMode_name = "acknowledgeMode";
    public static final String TYPE_NAME = "type";

    private Configuration configuration;
    private javax.jms.Connection connection;
    private Session session;

    /**
     * 0 消息列队
     * 1 主题
     */
    private int type = 0;

    public ActivemqConnection(Configuration configuration,javax.jms.Connection connection) {
        this.configuration = configuration;
        this.connection = connection;

        init();
    }

    private void init() {
        //transaction
        boolean transaction = false;
        Object transaction_ = configuration.get(TRANSACTION_NAME);
        if(transaction_ != null){
            if("true".equals(transaction_)){
                transaction = true;
            }else if("false".equals(transaction_)){
                transaction = false;
            }else{
                throw new NotSupportParamException("the value of " +TRANSACTION_NAME + " can not be " + transaction_);
            }

        }

        //auto mode
        int mode = 1;
        Object o = configuration.get(acknowledgeMode_name);
        if(o != null){
            o = String.valueOf(o);
            try {
                mode = Integer.parseInt(String.valueOf(o));
            } catch (NumberFormatException e) {
                throw new NumberFormatException("the " + acknowledgeMode_name + " config just only support number");
            }
        }else{
            mode = 1;
        }

        //queue or topic
        Object to=  configuration.get(TYPE_NAME);
        if(to != null){
            this.type = Integer.parseInt(String.valueOf(to));
            if(this.type != 0 && this.type != 1){
                throw new NotSupportParamException("the value of " +TYPE_NAME + " can not be " + to);
            }
        }else{
            this.type = 0;
        }

        try {
            this.session = this.connection.createSession(transaction, mode);
        } catch (JMSException e) {
            throw new CreateConnectionFailException("create session fail" , e);
        }
    }

    @Override
    public Consumer createConsumer(String topicName)throws CreateConnectionFailException {
        Destination destination = createDestination(topicName);
        try {
            MessageConsumer consumer = session.createConsumer(destination);

            return new ActivemqConsumer(consumer);

        } catch (JMSException e) {
            throw new CreateConnectionFailException("create activemq consumer fail", e);
        }
    }

    @Override
    public Producer createProducer(String topicName) throws CreateConnectionFailException{
        Destination destination = createDestination(topicName);
        try {
            MessageProducer producer = session.createProducer(destination);
            return new ActivemqProducer(producer,session);
        } catch (JMSException e) {
            throw new CreateConnectionFailException("create activemq producer fail", e);
        }
    }

    protected Destination createDestination(String topic){
        if(type == 0){
            return new ActiveMQQueue(topic);
        }else{
            return new ActiveMQTopic(topic);
        }
    }
}
