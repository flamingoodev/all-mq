package io.rushb.allmq.ms.connection;

import io.rushb.allmq.Configuration;
import io.rushb.allmq.ms.consumer.consumer.Consumer;
import io.rushb.allmq.ms.consumer.consumer.KafkaConsumer;
import io.rushb.allmq.ms.producer.KafkaProducer;
import io.rushb.allmq.ms.producer.Producer;
import io.rushb.allmq.util.Asserts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zxj<br>
 * 时间 2018/3/19 11:42
 * 说明 ...
 */
public class KafkaConnection implements Connection {
    public final static Logger LOG = LoggerFactory.getLogger(KafkaConnection.class);
    /**
     * set tur ,the kafka offset to auto commit
     */
    public static final String AUTO_COMMIT_KEY = "enable.auto.commit";

    private Configuration configuration;


    public KafkaConnection(Configuration configuration) {
        this.validConfiguration(configuration);
        this.configuration = configuration;
    }

    private void validConfiguration(Configuration configuration) {
        Object s = configuration.get(AUTO_COMMIT_KEY);
        if (s != null && !"true".equals(s)) {
            LOG.warn("the " + AUTO_COMMIT_KEY + " is not enable,this where set auto commit offset");
            configuration.add(AUTO_COMMIT_KEY, "true");
        }
    }


    @Override
    public Consumer createConsumer(String topic) {
        Asserts.notNull(configuration, "configuration is not set");
        return new KafkaConsumer(configuration, topic);
    }

    @Override
    public Producer createProducer(String topic) {
        Asserts.notNull(configuration, "configuration is not set");
        return new KafkaProducer(configuration, topic);
    }
}
