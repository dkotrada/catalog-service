package com.polarbookshop.catalogservice.domain;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class BookValidationTests {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    @DisplayName("When all tests correct then validation succeeds.")
    void whenAllTestsCorrectThenValidationSucceeds(){
        var book = new Book("1234567890", "Title", "Author", 9.99);
        Set<ConstraintViolation<Book>> violationSet = validator.validate(book);
        assertThat(violationSet).isEmpty();
    }

    @Test
    @DisplayName("When ISBN defined but incorrect then validation fails")
    void whenIsbnDefinedButIncorrectThenValidationFails(){
        var book = new Book("X234567890", "Title", "Author", 9.99);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("The ISBN format must be valid.");
    }
}
