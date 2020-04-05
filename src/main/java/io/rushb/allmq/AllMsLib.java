package io.rushb.allmq;



import io.rushb.allmq.ms.creator.ActivemqConnectionCreator;
import io.rushb.allmq.ms.creator.ConnectionCreator;
import io.rushb.allmq.ms.creator.KafkaConnectionCreator;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zxj<br>
 * 时间 2018/3/19 12:45
 * 说明 ...
 */
public class AllMsLib {
    private final static Map<MQ, ConnectionCreator> LIB = new HashMap<MQ, ConnectionCreator>();
    static {
        try{
            boolean kafkaExist = null != Class.forName("org.apache.kafka.clients.producer.Producer") ? true : false;
            LIB.put(MQ.KAFKA,new KafkaConnectionCreator());
        }catch (ClassNotFoundException e) {
            //kafka not exists
        }

        try{
            boolean kafkaExist = null != Class.forName("org.apache.activemq.ActiveMQConnection") ? true : false;
            LIB.put(MQ.ACTIVEMQ,new ActivemqConnectionCreator());
        }catch (ClassNotFoundException e) {
            //kafka not exists
        }
    }

    public static ConnectionCreator getConnectionCreator(MQ mq){
        ConnectionCreator connectionCreator = LIB.get(mq);
        if(connectionCreator == null){
            throw new NullPointerException("找不到" + mq + "对应的connectionCreator");
        }
        return connectionCreator;
    }
}
