package io.rushb.allmq.ms.creator;


import io.rushb.allmq.Configuration;
import io.rushb.allmq.exception.CreateConnectionFailException;
import io.rushb.allmq.ms.connection.ActivemqConnection;
import io.rushb.allmq.ms.connection.Connection;
import io.rushb.allmq.util.Asserts;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.JMSException;

/**
 * @author zxj<br>
 * 时间 2018/3/19 14:18
 * 说明 ...
 */
public class ActivemqConnectionCreator implements ConnectionCreator {
    private Configuration configuration;
    private String username;
    private String password;
    private String brokerURL;

    @Override
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
        this.resolveConfig(configuration);
    }

    private void resolveConfig(Configuration configuration){
        Object username = configuration.get("username");
        Asserts.notNull(username , "username is not set");
        this.username = username.toString();

        Object password = configuration.get("password");
        Asserts.notNull(password,"password is not set");
        this.password = password.toString();

        Object brokerURL = configuration.get("brokerURL");
        Asserts.notNull(brokerURL,"brokerUrl is not set");
        this.brokerURL = brokerURL.toString();
    }

    @Override
    public Connection create() throws CreateConnectionFailException {
        try {
            ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(username,password,brokerURL);
            javax.jms.Connection connection = activeMQConnectionFactory.createConnection();
            connection.start();
            return new ActivemqConnection(configuration,connection);
        } catch (JMSException e) {
            throw new CreateConnectionFailException("create activemq connection fail : " + e.getMessage() , e);
        }
    }
}
