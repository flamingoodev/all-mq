package io.rushb.allmq.message.producer;

import io.rushb.allmq.message.message.Configuration;
import io.rushb.allmq.message.message.KeyValueMessage;
import io.rushb.allmq.message.message.Message;
import io.rushb.allmq.util.Asserts;
import io.rushb.allmq.util.IoUtil;
import io.rushb.allmq.util.PropertiesUtil;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * kafka生产者
 *
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/5 22:14
 */
public class KafkaProducer implements Producer {

    private Configuration configuration;

    private org.apache.kafka.clients.producer.Producer<String, String> producer;

    protected String topic;

    public KafkaProducer(Configuration configuration) {
        Asserts.notNull(configuration, "Configuration can not be null");
        this.configuration = configuration;
        init();
    }

    public KafkaProducer(Configuration configuration, String topic) {
        this(configuration);
        Asserts.notNull(topic, "Topic can not be null");
        this.topic = topic;
    }

    private void init() {
        Properties properties = PropertiesUtil.convert(configuration);
        producer = new org.apache.kafka.clients.producer.KafkaProducer<>(properties);
    }

    @Override
    public void close() {
        IoUtil.close(producer);
    }

    /**
     * send the message
     *
     * @param topic   send topic
     * @param message send data
     */
    public void sendMessage(String topic, Message message) {
        Asserts.notNull(message, "Message not be null");
        if (message instanceof KeyValueMessage) {
            KeyValueMessage keyValueData = (KeyValueMessage) message;
            producer.send(new ProducerRecord<>(topic, keyValueData.getKey(), keyValueData.getData()));
        } else {
            producer.send(new ProducerRecord<>(topic, "", message.getData()));
        }
        producer.flush();
    }

    /**
     * send the message by default topic
     *
     * @param message message
     */
    @Override
    public void sendMessage(Message message) {
        this.sendMessage(this.topic, message);
    }
}
