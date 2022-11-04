package org.example.broker;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.jms.pool.PooledConnectionFactory;


public class Broker {

    // Specify the connection parameters.
    private static final String WIRE_LEVEL_ENDPOINT
            = "ssl://b-3dc6b128-c769-42d3-ac48-1e5f61873ae5-1.mq.eu-central-1.amazonaws.com:61617";
    private static final String ACTIVE_MQ_USERNAME = "olexandrsmit";
    private static final String ACTIVE_MQ_PASSWORD = "8h5CHafSR4esUak";

    // Create a connection factory.
    private static final ActiveMQConnectionFactory connectionFactory =
            new ActiveMQConnectionFactory(WIRE_LEVEL_ENDPOINT);

    private Broker() {
    }

    public static PooledConnectionFactory createPooledConnectionFactory(
            ActiveMQConnectionFactory connectionFactory) {
        // Create a pooled connection factory.
        final PooledConnectionFactory pooledConnectionFactory =
                new PooledConnectionFactory();
        pooledConnectionFactory.setConnectionFactory(connectionFactory);
        pooledConnectionFactory.setMaxConnections(10);
        return pooledConnectionFactory;
    }

    public static ActiveMQConnectionFactory createActiveMQConnectionFactory() {
        // Pass the username and password.
        connectionFactory.setUserName(ACTIVE_MQ_USERNAME);
        connectionFactory.setPassword(ACTIVE_MQ_PASSWORD);
        return connectionFactory;
    }
}