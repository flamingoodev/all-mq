package io.rushb.messageapp.controller;

import lombok.AllArgsConstructor;
import messagequeue.annotation.EnableMessageQueue;
import messagequeue.config.DataSource;
import messagequeue.factory.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 消息监听bean仓库
 *
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/29 12:09
 */
@Component
@AllArgsConstructor
@EnableMessageQueue(dataSource = DataSource.SQL_SERVER)
public class MessageBeanStore {

    private final BizService bizService;

    @Bean
    public void listenTest() {
        ConnectionFactory
                .getInstance()
                .getConnection()
                .createConsumer("test")
                .setMessageListener(bizService::listenTest);
    }

    @Bean
    public void listenTest1() {
        ConnectionFactory
                .getInstance()
                .getConnection()
                .createConsumer("test1")
                .setMessageListener(bizService::listenTest1);
    }
}
