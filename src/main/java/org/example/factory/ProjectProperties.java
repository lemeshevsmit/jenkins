package org.example.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

public class ProjectProperties {

    private static ProjectProperties instance = null;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectProperties.class);

    public String nameOfQueue;

    public String runTime;

    public String connectionLink;

    public String poisonMessage;


    private ProjectProperties()
    {
        Properties prop = new Properties();
        try {
            prop.load(ProjectProperties.class.getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            LOGGER.error("Properties file not load correct {} \n {}", e.getMessage(), e.getStackTrace());
        }
        nameOfQueue = prop.getProperty("my.active.mq.name");
        runTime = prop.getProperty("my.active.mq.time");
        connectionLink = prop.getProperty("my.active.mq.connection.link");
        poisonMessage = prop.getProperty("my.active.mq.poison.name");
    }


    public static ProjectProperties ProjectProperties()
    {
        // To ensure only one instance is created
        if (instance == null) {
            instance = new ProjectProperties();
        }
        return instance;
    }
}
