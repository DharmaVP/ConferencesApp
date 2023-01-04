package ua.com.vp.confapp.utils;

import ua.com.vp.confapp.exception.ValidationException;

import java.util.Arrays;

import static ua.com.vp.confapp.utils.RegexKeeper.*;

public final class Validator {


    public static void validateEmail(String email) throws ValidationException {
        validateFormat(email, REGEX_EMAIL, "Enter correct email");
    }

    public static void validatePassword(String password) throws ValidationException {
        validateFormat(password, REGEX_PASSWORD, "From 8 to 10 eight characters, at least one letter, one number and one special character:");
    }

    public static void validateConfirmPassword(String password, String confirmPassword) throws ValidationException {
        if (!password.equals(confirmPassword))
            throw new ValidationException("Passwords don't match");
    }

    public static void validateNonEmptyFields(String... fields) throws ValidationException {
        for (String field : fields) {
            if (field == null) {
                throw new ValidationException("Please, enter");
            }
        };
    }

    private static void validateFormat(String name, String regex, String message) throws ValidationException {
        if (name == null || !name.matches(regex))
            throw new ValidationException(message);
    }
}
