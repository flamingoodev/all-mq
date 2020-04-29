package io.rushb.messageappbuild;

import messagequeue.message.message.Message;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/28 13:53
 */
@Service
public class BizService {
    public void listen(Message message) {
        System.out.println("接收到消息：" + message);
        System.out.println("业务处理开始...");
        System.out.println("业务处理中：输出数据 -> " + message);
        System.out.println("业务处理完成...");
    }
}
