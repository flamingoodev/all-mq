package io.rushb.allmq.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 消息线程池
 *
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/5 23:06
 */
public class MessageThreadFactory implements ThreadFactory {
    public final static Logger logger = LoggerFactory.getLogger(MessageThreadFactory.class);
    private final String namePrefix;
    private final AtomicInteger nextId = new AtomicInteger(1);

    public MessageThreadFactory(String whatFeatureOfGroup) {
        this.namePrefix = "From MessageThreadFactory's" + whatFeatureOfGroup + "-Worker-";
    }

    @Override
    public Thread newThread(Runnable task) {
        String name = namePrefix + nextId.getAndIncrement();
        Thread thread = new Thread(null, task, name, 0);
        logger.info("Thread " + name + " created");
        return thread;
    }
}
