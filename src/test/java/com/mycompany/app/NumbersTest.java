package com.mycompany.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NumbersTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(NumbersTest.class);
    Numbers numbers;
    Resource resource;

    @BeforeAll
    static void initSystemProperties() {
        System.setProperty("type", "integer");
    }

    @BeforeEach
    void init() {
        resource = new Resource();
        resource.setPropMin("1");
        resource.setPropMax("9");
        resource.setPropInc("1");
        numbers = new Numbers(false, resource);
    }

    @AfterEach
    void clear() {
        numbers = null;
    }

    @ParameterizedTest
    @CsvSource({"short", "byte", "int", "long", "biginteger", "bigdecimal", "double", "float"})
    void calculate(String type) {
        LOGGER.info("Testing type {}", type);
        System.setProperty("type", type);
        String result = numbers.calculate();
        assertEquals(type, result);
        LOGGER.info("Status: OK");
    }

    @Test
    void calculateWithoutSystemParameterType() {
        LOGGER.info("Testing with default type int");
        System.clearProperty("type");
        String expected = "integer";
        String result = numbers.calculate();
        assertEquals(expected, result);
        LOGGER.info("Status: OK");
    }

    @Test
    void calculateWithErrorSystemParameterType() {
        LOGGER.info("Testing with error type");
        System.setProperty("type", "type");
        String expected = "integer";
        String result = numbers.calculate();
        assertEquals(expected, result);
        LOGGER.info("Status: OK");
    }

//    @Test
//    void calculateShouldThrowIOExceptionWhenResourceIsNull() {
//        LOGGER.info("Testing when resource is null");
//        //given
//        String expected = "Cannot invoke \"org.example.Resource.getPropMax()\" because \"this.resource\" is null";
//        numbers = new Numbers(false, null);
//        //when
//        NullPointerException exception = assertThrows(NullPointerException.class, () -> numbers.calculate());
//        //then
//        assertEquals(expected, exception.getMessage());
//        LOGGER.info("Status: OK");
//    }

    @ParameterizedTest
    @CsvSource({"short, 50000", "byte, 300"})
    void calculateShouldThrowNumberFormatExceptionWhenOutOfRangeForInputType(String type, String value) {
        LOGGER.info("Testing {} when input parameter have out of range: {}",type,value);
        //given
        System.setProperty("type", type);
        resource.setPropMin(value);
        String expected = "Value out of range. Value:\"" + value + "\" Radix:10";
        //when
        NumberFormatException exception = assertThrows(NumberFormatException.class, () -> numbers.calculate());
        //then
        assertEquals(expected, exception.getMessage());
        LOGGER.info("Status: OK");
    }

    @ParameterizedTest
    @CsvSource({"short, trtr", "byte, *300", "int, 31474B3647", "long , 19223372036854775807"})
    void calculateShouldThrowNumberFormatExceptionWhenInputIncorrectValue(String type, String value) {
        LOGGER.info("Testing {} when input parameter is incorrect: {}",type,value);
        //given
        System.setProperty("type", type);
        resource.setPropMin(value);
        String expected = "For input string: \"" + value + "\"";
        //when
        NumberFormatException exception = assertThrows(NumberFormatException.class, () -> numbers.calculate());
        //then
        assertEquals(expected, exception.getMessage());
        LOGGER.info("Status: OK");
    }
}