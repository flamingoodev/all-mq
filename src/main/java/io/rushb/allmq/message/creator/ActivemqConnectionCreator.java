package io.rushb.allmq.message.creator;


import io.rushb.allmq.exception.CreateConnectionFailException;
import io.rushb.allmq.message.connection.ActivemqConnection;
import io.rushb.allmq.message.connection.Connection;
import io.rushb.allmq.message.message.Configuration;
import io.rushb.allmq.util.Asserts;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.JMSException;

/**
 * ActiveMQ连接创建者
 *
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/5 22:14
 */
public class ActivemqConnectionCreator implements ConnectionCreator {

    private Configuration configuration;

    private String username;

    private String password;

    private String brokerUrl;

    @Override
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
        this.resolveConfig(configuration);
    }

    private void resolveConfig(Configuration configuration) {
        Object username = configuration.get("username");
        Asserts.notNull(username, "Username is not set");
        this.username = username.toString();

        Object password = configuration.get("password");
        Asserts.notNull(password, "Password is not set");
        this.password = password.toString();

        Object brokerUrl = configuration.get("brokerURL");
        Asserts.notNull(brokerUrl, "Broker url is not set");
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
