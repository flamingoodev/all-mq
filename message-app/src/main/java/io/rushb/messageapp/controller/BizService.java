package io.rushb.messageapp.controller;

import messagequeue.message.message.Message;
import org.springframework.stereotype.Service;

/**
 * 业务处理层
 *
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/29 11:48
 */
@Service
public class BizService {
    public void listenTest(Message message) {
        System.out.println("接收到消息：" + message);
        System.out.println("业务处理开始...");
        System.out.println("业务处理中：输出数据 -> " + message);
        System.out.println("业务处理完成...");
    }

    public void listenTest1(Message message) {
        System.out.println("接收到消息：" + message);
        System.out.println("业务处理开始...");
        System.out.println("业务处理中：输出数据 -> " + message);
        System.out.println("业务处理完成...");
    }
}
