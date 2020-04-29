### all-mq

​		本项目融合了ActiveMQ和Kafka，可以通过数据库配置或者自定义配置来使用消息队列，提供消息队列底层无关的使用方式。提供了MySQL和SQLServer两种数据库的配置方案。表结构可以按需修改，启动注解可以优化，可拆分成启动注解@EnableMessageQueue（加载message-queue.jar包中的bean）和@MessageBeanStore（消息bean仓库）两个注解，@EnableMessageQueue注解到Spring Boot启动类，MessageBeanStore注解到Message Bean仓库，或者单独再写个@ListenMQ（topics=“test”）的注解去监听消息。SQL脚本可以自行优化（此处复用了公司项目中的SQL脚本）。在公司项目中用到了ActiveMQ和Kafka的混合使用，其实是因为老项目使用了ActiveMQ，新项目要用Kafka，所以产生了这个需求，正常实际生产环境应该不存在此类需求，故此项目不再维护。若要在生产中使用此项目，建议多做优化和测试。
​		此项目参考了gitee上名为all-mq的项目，十分感谢大佬。

- 需安装ActiveMQ或者Kafka
- message-app是通过数据库配置使用的案例
- message-app-build是通过自定义配置使用的案例