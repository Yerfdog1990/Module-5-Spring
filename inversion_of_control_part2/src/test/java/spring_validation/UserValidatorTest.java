package spring_validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserValidatorTest {

    @Test
    void testUserValidator() {
        User user = new User("", "invalid email");
        Validator validator = new UserValidator();
        Errors errors = new BeanPropertyBindingResult(user, "user");
        validator.validate(user, errors);
        assertTrue(errors.hasErrors());
        assertTrue(errors.hasFieldErrors("userName"));
        assertEquals("userName.empty", errors.getFieldError("userName").getCode());
        assertTrue(errors.hasFieldErrors("email"));
        assertEquals("email.invalid", errors.getFieldError("email").getCode());
        //assertEquals("email.empty", errors.getFieldError("email").getCode());
    }
}
