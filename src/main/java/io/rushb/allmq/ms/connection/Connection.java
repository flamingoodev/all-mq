package io.rushb.allmq.ms.connection;


import io.rushb.allmq.exception.CreateConnectionFailException;
import io.rushb.allmq.ms.consumer.consumer.Consumer;
import io.rushb.allmq.ms.producer.Producer;

/**
 * @author zxj<br>
 * 时间 2018/3/19 11:26
 * 说明 ...
 */
public interface Connection{



    /**
     * 创建consumer
     * @return
     */
    Consumer createConsumer(String topic) throws CreateConnectionFailException;

    /**
     * 创建producer
     * @return
     */
    Producer createProducer(String topic) throws CreateConnectionFailException;


}
