package messagequeue.annotation;

import messagequeue.config.DataSource;
import messagequeue.config.MessageQueueConfig;
import messagequeue.config.MySqlDataSourceConfig;
import messagequeue.config.SqlServerDataSourceConfig;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;


/**
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/27 14:39
 */
public class MessageQueueImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(
                annotationMetadata.getAnnotationAttributes(EnableMessageQueue.class.getName()));
        assert annotationAttributes != null;
        return annotationAttributes.containsValue(DataSource.SQL_SERVER)
                ? new String[]{MessageQueueConfig.class.getName(), SqlServerDataSourceConfig.class.getName()}
                : new String[]{MessageQueueConfig.class.getName(), MySqlDataSourceConfig.class.getName()};
    }
}
