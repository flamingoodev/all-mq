package messagequeue.message.consumer.consumer;


import messagequeue.factory.MessageThreadFactory;
import messagequeue.message.consumer.listener.MessageListener;
import messagequeue.message.message.MqConfiguration;
import messagequeue.message.message.KeyValueMessage;
import messagequeue.util.Asserts;
import messagequeue.util.IoUtil;
import messagequeue.util.PropertiesUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * kafka消费者
 *
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/5 22:14
 */
public class KafkaConsumer implements Consumer {

    public final static Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    private MqConfiguration configuration;

    private String topic;

    private org.apache.kafka.clients.consumer.Consumer<String, String> consumer;

    private MessageListener messageListener;

    /**
     * is true,will receive message
     */
    private boolean receive = false;

    public KafkaConsumer(MqConfiguration configuration, String topic) {
        Asserts.notNull(configuration, "configuration can not be null");
        Asserts.notNull(topic, "topic can not be null");
        this.configuration = configuration;
        this.topic = topic;
        init();
    }

    private void init() {
        Properties properties = PropertiesUtil.convert(configuration);
        consumer = new org.apache.kafka.clients.consumer.KafkaConsumer<>(properties);
        consumer.subscribe(Arrays.asList(topic));
    }

    @Override
    public void close() throws IOException {
        IoUtil.close(consumer);
    }

    @Override
    public synchronized void setMessageListener(MessageListener messageListener) {
        if (this.messageListener == null) {
            this.receive = true;
            this.messageListener = messageListener;
            new MessageThreadFactory("MessageListener").newThread(new Receive()).start();
        }
        this.messageListener = messageListener;
    }

    private class Receive implements Runnable {

        @Override
        public void run() {
            while (receive) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    if (messageListener != null) {
                        messageListener.onMessage(new KeyValueMessage(record.key(), record.value()));
                    }
                }
            }
        }
    }
}
