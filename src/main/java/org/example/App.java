package org.example;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.example.broker.Broker;
import org.example.broker.Consumer;
import org.example.broker.Producer;
import org.example.factory.ActivePojoFactory;
import org.example.factory.ProjectProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Hello world!
 */
public class App {

    private static final ActiveMQConnectionFactory connectionFactory =
            Broker.createActiveMQConnectionFactory();
    private static final PooledConnectionFactory pooledConnectionFactory =
            Broker.createPooledConnectionFactory(connectionFactory);
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        ProjectProperties properties = ProjectProperties.ProjectProperties();
        String property = System.getProperty("N");
        int count = (property == null) ? 0 : Integer.parseInt(property);
        long runTime = Long.parseLong(properties.runTime);

        ActivePojoFactory activePojoFactory = new ActivePojoFactory();
        long startTime = LocalTime.now().getLong(ChronoField.SECOND_OF_DAY);
        Producer producer = new Producer();
        producer.sentMessages(pooledConnectionFactory, IntStream.iterate(0,
                        i -> ((LocalTime.now().getLong(ChronoField.SECOND_OF_DAY) - startTime) <= runTime) && (i < count),
                        i -> i + 1)
                .mapToObj(i -> activePojoFactory.create())
                .map(activePojoFactory::createJson)
                .collect(Collectors.toList()));

        Consumer consumer = new Consumer();
        consumer.readMessages(connectionFactory);

        pooledConnectionFactory.stop();
    }
}

/*
        IntStream.iterate(0, i -> ((LocalTime.now().getLong(ChronoField.SECOND_OF_DAY) - startTime) <= runTime) && (i < count), i -> i + 1)
                .mapToObj(i -> activePojoFactory.create())
                .map(activePojoFactory::createJson)
                .forEach(message -> producer.sentMessages(pooledConnectionFactory, message));

        var messages = Stream.generate(activePojoFactory::create)
                .limit(count)
                .map(activePojoFactory::createJson)
                .collect(Collectors.toList());
 */