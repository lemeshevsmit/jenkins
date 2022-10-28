package com.mycompany.app;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;


class ResourceTest {

    Resource resource;

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceTest.class);

    @BeforeAll
    static void beforeAll(){
        LOGGER.info("Start testing connection to properties file");
    }

    @BeforeEach
    void init() {
        resource = new Resource();
        try {
            Properties prop = new Properties();
            prop.load(Resource.class.getClassLoader().getResourceAsStream("application.properties"));
            resource.setPropMax(prop.getProperty("max"));
            resource.setPropMin(prop.getProperty("min"));
            resource.setPropInc(prop.getProperty("inc"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    void clear() {
        resource = null;
    }

    @Test
    void getPropMin() {
        String expected="1";
        Assertions.assertEquals(expected,resource.getPropMin());
        LOGGER.info("Status OK");
    }

    @Test
    void getPropMax() {
        String expected="9";
        Assertions.assertEquals(expected,resource.getPropMax());
        LOGGER.info("Status OK");
    }

    @Test
    void getPropInc() {
        String expected="1";
        Assertions.assertEquals(expected,resource.getPropInc());
        LOGGER.info("Status OK");
    }

    @Test
    void setPropMin() {
        String expected="100";
        resource.setPropMin("100");
        Assertions.assertEquals(expected,resource.getPropMin());
        LOGGER.info("Status OK");
    }

    @Test
    void setPropMax() {
        String expected="100";
        resource.setPropMax("100");
        Assertions.assertEquals(expected,resource.getPropMax());
        LOGGER.info("Status OK");
    }

    @Test
    void setPropInc() {
        String expected="100";
        resource.setPropInc("100");
        Assertions.assertEquals(expected,resource.getPropInc());
        LOGGER.info("Status OK");
    }
}