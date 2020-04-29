package io.rushb.messageappbuild;

import messagequeue.MqTemplate;
import messagequeue.message.message.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/9 20:11
 */
@RestController
@RequestMapping("message")
public class MessageController {

    @GetMapping("/send")
    public String send() {
        MqTemplate.send("test", new Message("This is a test message."));
        return "message send success";
    }

    @GetMapping("/send1")
    public String send1() {
        MqTemplate.send("test1", new Message("This is a test1 message."));
        return "message send success";
    }
}
