package com.mycompany.app;

import com.mycompany.app.Numbers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NumbersTest {

    Numbers numbers;

    @BeforeAll
    static void initSystemProperties() {
        System.setProperty("type", "integer");
    }

    @BeforeEach
    void init() {
        numbers = new Numbers();
    }

    @AfterEach
    void clear() {
        numbers = null;
    }

    @ParameterizedTest
    @CsvSource({"short", "byte", "int", "long", "biginteger", "bigdecimal", "double", "float"})
    void calculate(String type) throws IOException {
        System.setProperty("type", type);
        String result = numbers.calculate();
        assertEquals(type, result);
    }

    @Test
    void calculateWithoutSystemParameterType() throws IOException {
        System.clearProperty("type");
        String expected = "integer";
        String result = numbers.calculate();
        assertEquals(expected, result);
    }

    @Test
    void calculateWithErrorSystemParameterType() throws IOException {
        System.setProperty("type", "type");
        String expected = "integer";
        String result = numbers.calculate();
        assertEquals(expected, result);
    }

//    @Test
//    void calculateShouldThrowIOExceptionWhenPropertiesFileIsNull() {
//        //given
//        String expected = "File properties not found: inStream parameter is null";
//
//        //when
//        NullPointerException exception = assertThrows(NullPointerException.class, () -> numbers.calculate());
//        //then
//        assertEquals(expected, exception.getMessage());
//    }
//
//    @Test
//    void calculateShouldThrowNumberFormatExceptionWhenOutOfRangeForInputType() {
//        //given
//        System.setProperty("type", "byte");
//        Properties prop = new Properties();
//        try {
//            prop.load(Numbers.class.getClassLoader().getResourceAsStream("application.properties"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        String value = prop.getProperty("min");
//        String expected = "Value out of range. Value:\"" + value + "\" Radix:10";
//        //when
//        NumberFormatException exception = assertThrows(NumberFormatException.class, () -> numbers.calculate());
//        //then
//        assertEquals(expected, exception.getMessage());
//    }
}