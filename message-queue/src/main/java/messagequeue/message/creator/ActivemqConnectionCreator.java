package messagequeue.message.creator;


import messagequeue.message.message.MqConfiguration;
import messagequeue.exception.CreateConnectionFailException;
import messagequeue.message.connection.ActivemqConnection;
import messagequeue.message.connection.Connection;
import messagequeue.util.Asserts;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.JMSException;

/**
 * ActiveMQ连接创建者
 *
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/5 22:14
 */
public class ActivemqConnectionCreator implements ConnectionCreator {

    private MqConfiguration configuration;

    private String username;

    private String password;

    private String brokerUrl;

    @Override
    public void setConfiguration(MqConfiguration configuration) {
        this.configuration = configuration;
        this.resolveConfig(configuration);
    }

    private void resolveConfig(MqConfiguration configuration) {
        Object username = configuration.get("username");
        Asserts.notNull(username, "Username is not set");
        this.username = username.toString();

        Object password = configuration.get("password");
        Asserts.notNull(password, "Password is not set");
        this.password = password.toString();

        Object brokerUrl = configuration.get("brokerURL");
        Asserts.notNull(brokerUrl, "BrokerUrl is not set");
        this.brokerUrl = brokerUrl.toString();
    }

    @Override
    public Connection create() throws CreateConnectionFailException {
        try {
            ActiveMQConnectionFactory activeMqConnectionfactory = new ActiveMQConnectionFactory(username, password, brokerUrl);
            javax.jms.Connection connection = activeMqConnectionfactory.createConnection();
            connection.start();
            return new ActivemqConnection(configuration, connection);
        } catch (JMSException e) {
            throw new CreateConnectionFailException("Create activemq connection fail : " + e.getMessage(), e);
        }
    }
}
