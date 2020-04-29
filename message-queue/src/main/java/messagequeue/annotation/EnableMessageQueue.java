package messagequeue.annotation;

import messagequeue.config.DataSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/20 15:49
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({MessageQueueImportSelector.class})
@ComponentScan({"messagequeue"})
public @interface EnableMessageQueue {
    String dataSource() default DataSource.MYSQL;
}
