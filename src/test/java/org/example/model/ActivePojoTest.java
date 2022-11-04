package org.example.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.Month;
import java.util.Locale;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ActivePojoTest {

    ActivePojo activePojo;

    private static Validator validator;
    private static final Logger LOGGER = LoggerFactory.getLogger(ActivePojoTest.class);

    private static final String NAME = "Lemesheva Regina";
    private static final Long COUNT = 10L;
    private static final LocalDate DATE = LocalDate.of(2021, Month.JUNE, 8);


    @BeforeAll
    static void beforeAll() {
        LOGGER.info("Start testing pojo model");
        Locale.setDefault(Locale.ENGLISH);
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @BeforeEach
    void init() {
        activePojo = new ActivePojo();
        activePojo.setCreatedAt(DATE);
        activePojo.setCount(COUNT);
        activePojo.setName(NAME);
    }

    @AfterEach
    void clear() {
        activePojo = null;
    }

    @Test
    void getName(TestInfo testInfo) {
        LOGGER.info("Testing: {}", testInfo.getTestMethod().get().getName());
        Assertions.assertEquals(NAME, activePojo.getName());
        LOGGER.info("Status OK");
    }

    @Test
    void setName(TestInfo testInfo) {
        LOGGER.info("Testing: {}", testInfo.getTestMethod().get().getName());
        String expected = "test-message";
        activePojo.setName(expected);
        Assertions.assertEquals(expected, activePojo.getName());
        LOGGER.info("Status OK");
    }

    @Test
    void getCount(TestInfo testInfo) {
        LOGGER.info("Testing: {}", testInfo.getTestMethod().get().getName());
        Assertions.assertEquals(COUNT, activePojo.getCount());
        LOGGER.info("Status OK");
    }

    @Test
    void setCount(TestInfo testInfo) {
        LOGGER.info("Testing: {}", testInfo.getTestMethod().get().getName());
        Long expected = 55L;
        activePojo.setCount(expected);
        Assertions.assertEquals(expected, activePojo.getCount());
        LOGGER.info("Status OK");
    }

    @Test
    void getCreatedAt(TestInfo testInfo) {
        LOGGER.info("Testing: {}", testInfo.getTestMethod().get().getName());
        Assertions.assertEquals(DATE, activePojo.getCreatedAt());
        LOGGER.info("Status OK");
    }

    @Test
    void setCreatedAt(TestInfo testInfo) {
        LOGGER.info("Testing: {}", testInfo.getTestMethod().get().getName());
        LocalDate expected = LocalDate.now();
        activePojo.setCreatedAt(expected);
        Assertions.assertEquals(expected, activePojo.getCreatedAt());
        LOGGER.info("Status OK");
    }

    @Test
    void toString(TestInfo testInfo) {
        LOGGER.info("Testing: {}", testInfo.getTestMethod().get().getName());
        String expected = "ActivePojo{name='" + NAME +
                "', count=" + COUNT +
                ", createdAt=" + DATE + "}";
        Assertions.assertEquals(expected, activePojo.toString());
        LOGGER.info("Status OK");
    }

    @Test
    void isNotCorrectNameLength(TestInfo testInfo) throws NoSuchFieldException {
        LOGGER.info("Testing: {}", testInfo.getTestMethod().get().getName());
        String name = "Vova";
        activePojo.setName(name);
        var rule = ActivePojo.class
                .getDeclaredField("name")
                .getAnnotation(Length.class)
                .min();

        Set<ConstraintViolation<ActivePojo>> constraintViolations =
                validator.validateProperty(activePojo,"name");

        assertEquals(1, constraintViolations.size());
        assertEquals("name length must be greater than or equal to " + rule,
                constraintViolations.iterator().next().getMessage());
        LOGGER.info("Status OK");
    }

    @Test
    void isNotCorrectNamePattern(TestInfo testInfo) {
        LOGGER.info("Testing: {}", testInfo.getTestMethod().get().getName());
        String name = "Test-test-test";
        activePojo.setName(name);

        Set<ConstraintViolation<ActivePojo>> constraintViolations =
                validator.validateProperty(activePojo,"name");

        assertEquals(1, constraintViolations.size());
        assertEquals("Bad formed name: " + name + " must contains: a",
                constraintViolations.iterator().next().getMessage());
        LOGGER.info("Status OK");
    }

    @Test
    void isNotCorrectCountMin(TestInfo testInfo) throws NoSuchFieldException {
        LOGGER.info("Testing: {}", testInfo.getTestMethod().get().getName());
        Long count = 1L;
        activePojo.setCount(count);
        var rule = ActivePojo.class
                .getDeclaredField("count")
                .getAnnotation(Min.class)
                .value();

        Set<ConstraintViolation<ActivePojo>> constraintViolations =
                validator.validateProperty(activePojo,"count");

        assertEquals(1, constraintViolations.size());
        assertEquals("must be greater than or equal to " + rule,
                constraintViolations.iterator().next().getMessage());
        LOGGER.info("Status OK");
    }

    @Test
    void isNotCorrectCreatedAtDate(TestInfo testInfo) {
        LOGGER.info("Testing: {}", testInfo.getTestMethod().get().getName());
        LocalDate date = LocalDate.of(3000, Month.JANUARY, 1);
        activePojo.setCreatedAt(date);

        Set<ConstraintViolation<ActivePojo>> constraintViolations =
                validator.validateProperty(activePojo,"createdAt");

        assertEquals(1, constraintViolations.size());
        assertEquals("must be a date in the past or in the present",
                constraintViolations.iterator().next().getMessage());
        LOGGER.info("Status OK");
    }
}