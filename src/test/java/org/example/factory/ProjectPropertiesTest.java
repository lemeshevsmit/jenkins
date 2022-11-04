package org.example.factory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class ProjectPropertiesTest {

    @Test
    void constructor(){
        try {
            ProjectProperties properties = ProjectProperties.ProjectProperties();
            Properties prop = new Properties();
            prop.load(ProjectProperties.class.getClassLoader().getResourceAsStream("application.properties"));
            String nameOfQueue = prop.getProperty("my.active.mq.name");
            String runTime = prop.getProperty("my.active.mq.time");
            String connectionLink = prop.getProperty("my.active.mq.connection.link");
            String poisonText = prop.getProperty("my.active.mq.poison.name");
            Assertions.assertEquals(nameOfQueue,properties.nameOfQueue);
            Assertions.assertEquals(runTime,properties.runTime);
            Assertions.assertEquals(connectionLink,properties.connectionLink);
            Assertions.assertEquals(poisonText,properties.poisonMessage);
        } catch (IOException ignored) {
        }
    }
}