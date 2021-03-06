package messagequeue.message.connection;

import messagequeue.message.consumer.consumer.Consumer;
import messagequeue.message.consumer.consumer.KafkaConsumer;
import messagequeue.message.message.MqConfiguration;
import messagequeue.message.producer.KafkaProducer;
import messagequeue.message.producer.Producer;
import messagequeue.util.Asserts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * kafka连接类
 *
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/5 22:14
 */
public class KafkaConnection implements Connection {

    public final static Logger logger = LoggerFactory.getLogger(KafkaConnection.class);
    /**
     * set kafka offset to auto commit
     */
    public static final String AUTO_COMMIT_KEY = "enable.auto.commit";

    private MqConfiguration configuration;

    public KafkaConnection(MqConfiguration configuration) {
        this.validConfiguration(configuration);
        this.configuration = configuration;
    }

    private void validConfiguration(MqConfiguration configuration) {
        Object s = configuration.get(AUTO_COMMIT_KEY);
        if (s != null && !Boolean.toString(true).equals(s)) {
            logger.warn("The " + AUTO_COMMIT_KEY + " is not enable,this where set auto commit offset");
            configuration.add(AUTO_COMMIT_KEY, "true");
        }
    }


    @Override
    public Consumer createConsumer(String topic) {
        Asserts.notNull(configuration, "Configuration is not set");
        return new KafkaConsumer(configuration, topic);
    }

    @Override
    public Producer createProducer(String topic) {
        Asserts.notNull(configuration, "Configuration is not set");
        return new KafkaProducer(configuration, topic);
    }
}
