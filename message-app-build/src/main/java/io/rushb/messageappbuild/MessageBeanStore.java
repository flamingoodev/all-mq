package io.rushb.messageappbuild;

import lombok.AllArgsConstructor;
import messagequeue.factory.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/28 13:52
 */
@Component
@AllArgsConstructor
public class MessageBeanStore {
    private final BizService bizService;

    /**
     * 推荐使用，定义一个MQ监听的类，只用来监听所有的消息，监听到后去调用业务方法
     */
    @Bean
    public void listen() {
        ConnectionFactory
                .getInstance()
                .getConnection()
                .createConsumer("test")
                .setMessageListener(bizService::listen);
    }

    /**
     * 直接监听
     */
    @Bean
    void listen1() {
        ConnectionFactory
                .getInstance()
                .getConnection()
                .createConsumer("test1")
                .setMessageListener(message -> {
                    // do something...
                    System.out.println("message listen success:");
                    System.out.println("test1 -> " + message);
                });
    }
}
