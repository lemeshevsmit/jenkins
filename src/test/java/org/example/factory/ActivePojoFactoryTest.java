package org.example.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import org.example.model.ActivePojo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class ActivePojoFactoryTest {

    @Test
    void create() {
        ActivePojoFactory activePojoFactory = new ActivePojoFactory();
        var result = Optional.ofNullable(activePojoFactory.create());
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(ActivePojo.class,activePojoFactory.create().getClass());
    }

    @Test
    void createJsonWithErrorMessage() throws JsonProcessingException {
        try(ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            var validator = factory.getValidator();
            var errors =
                    validator.validateValue(ActivePojo.class, "name", "Viv");
            var expected = "{\"errors\":[" +
                    "\"Bad formed name: Viv must contains: a\"," +
                    "\"name length must be greater than or equal to 7\"]}";

            var result = new ActivePojoFactory().createJsonWithErrorMessage(errors);
            Assertions.assertEquals(expected,result);

        }
    }
}