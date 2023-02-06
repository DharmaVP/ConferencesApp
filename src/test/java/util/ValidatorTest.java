package util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ua.com.vp.confapp.exception.ValidationException;
import ua.com.vp.confapp.utils.Validator;

import java.lang.reflect.Executable;

import static org.junit.jupiter.api.Assertions.*;
import static ua.com.vp.confapp.exception.ExceptionMessage.ENTER_CORRECT_EMAIL;

class ValidatorTest {

// ToDo parametrized test with correct and incorrect emails


    static String[] getWrongEmails() {
        return new String[] {
                null, "", "@gmail.com", "vladimir..po@gmail.com", "vladimir.po@gmail..com", "vladimir@gmail", "vladimirgmail.com"
        };
    }

    static String[] getRightEmails() {
        return new String[] {
                "vladimir@gmail.com", "cat@analytics.ua"
        };
    }

    @ParameterizedTest
    @MethodSource(value = "getWrongEmails")
    void ensureThatEmailValidatorReturnsThrows(String incorrectEmail) {
        assertThrows(ValidationException.class, () ->
                Validator.validateEmail(incorrectEmail));

    }


    @Test
    void ensureThatEmailValidatorReturnsTrueForValidEmail() {
        Exception exception = assertThrows(ValidationException.class, () ->
                Validator.validateEmail("larsgmail.com"));
        assertEquals(ENTER_CORRECT_EMAIL, exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource(value = "getRightEmails")
    void ensureEverythingOk(String rightEmails) {
        assertDoesNotThrow(() -> Validator.validateEmail(rightEmails));
    }
}
