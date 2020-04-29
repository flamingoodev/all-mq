package messagequeue.message.producer;

import messagequeue.message.message.MqConfiguration;
import messagequeue.message.message.KeyValueMessage;
import messagequeue.message.message.Message;
import messagequeue.util.Asserts;
import messagequeue.util.IoUtil;
import messagequeue.util.PropertiesUtil;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.IOException;
import java.util.Properties;

/**
 * kafka生产者
 *
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/5 22:14
 */
public class KafkaProducer implements Producer {

    private MqConfiguration configuration;

    private org.apache.kafka.clients.producer.Producer<String, String> producer;

    protected String topic;

    public KafkaProducer(MqConfiguration configuration) {
        Asserts.notNull(configuration, "configuration can not be null");
        this.configuration = configuration;
        init();
    }

    public KafkaProducer(MqConfiguration configuration, String topic) {
        this(configuration);
        Asserts.notNull(topic, "topic can not be null");
        this.topic = topic;
    }

    private void init() {
        Properties properties = PropertiesUtil.convert(configuration);
        producer = new org.apache.kafka.clients.producer.KafkaProducer<String, String>(properties);
    }

    @Override
    public void close() throws IOException {
        IoUtil.close(producer);
    }

    /**
     * @param topic   send topic
     * @param message send data
     */
    public void sendMessage(String topic, Message message) {
        Asserts.notNull(message, "message not be null");
        if (message instanceof KeyValueMessage) {
            KeyValueMessage keyValueData = (KeyValueMessage) message;
            producer.send(new ProducerRecord<String, String>(topic, keyValueData.getKey(), keyValueData.getData()));
        } else {
            producer.send(new ProducerRecord<String, String>(topic, "", message.getData()));
        }
        producer.flush();
    }

    /**
     * send the message by default topic
     *
     * @param message
     */
    @Override
    public void sendMessage(Message message) {
        this.sendMessage(this.topic, message);
    }
}
