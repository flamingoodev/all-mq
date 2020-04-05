package io.rushb.allmq.ms.consumer.consumer;


import io.rushb.allmq.Configuration;
import io.rushb.allmq.ms.consumer.listener.MessageListener;
import io.rushb.allmq.ms.message.KeyValueMessage;
import io.rushb.allmq.util.Asserts;
import io.rushb.allmq.util.IOUtil;
import io.rushb.allmq.util.PropertiesUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * @author zxj<br>
 * 时间 2018/3/19 14:50
 * 说明 ...
 */
public class KafkaConsumer implements Consumer {
    public final static Logger LOG = LoggerFactory.getLogger(KafkaConsumer.class);


    private Configuration configuration;
    private String topic;

    private org.apache.kafka.clients.consumer.Consumer<String, String> consumer;

    private MessageListener messageListener;

    /**
     * is true,will receive message
     */
    private boolean receive = false;

    public KafkaConsumer(Configuration configuration, String topic) {
        Asserts.notNull(configuration, "configuration can not be null");
        Asserts.notNull(topic, "topic can not be null");
        this.configuration = configuration;
        this.topic = topic;
        init();
    }

    private void init() {


        Properties properties = PropertiesUtil.convert(configuration);

        consumer = new org.apache.kafka.clients.consumer.KafkaConsumer<String, String>(properties);
        consumer.subscribe(Arrays.asList(topic));

    }

    @Override
    public void close() throws IOException {
        IOUtil.close(consumer);

    }

    @Override
    public synchronized void setMessageListener(MessageListener messageListener) {
        if (this.messageListener == null) {
            this.receive = true;
            this.messageListener = messageListener;
            new Thread(new Receive()).start();
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
