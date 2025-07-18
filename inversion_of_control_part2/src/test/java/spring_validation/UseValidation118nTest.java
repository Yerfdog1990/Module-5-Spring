package spring_validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UseValidation118nTest {
    private MessageSource messageSource;
    private Validator validator;

    @BeforeEach
    void setUp(){
        ApplicationContext context = new AnnotationConfigApplicationContext(ValidationTestConfig.class);
        messageSource = context.getBean(MessageSource.class);
        validator = new UserValidator();
    }

    @Test
    void testValidationInEnglish() {
        Locale.setDefault(Locale.ENGLISH); // Set the default locale to English
        User user = new User("", "invalid-email");
        Errors errors = new BeanPropertyBindingResult(user, "user");
        validator.validate(user, errors);

        String englishErrorMessage = messageSource.getMessage(errors.getFieldError("userName").getCode(), null, Locale.ENGLISH);
        assertEquals("Username cannot be empty.", englishErrorMessage);
    }

    @Test
    void testValidationInFrench() {
        Locale.setDefault(Locale.FRENCH); // Set the default locale to French
        User user = new User("", "invalid-email");
        Errors errors = new BeanPropertyBindingResult(user, "user");
        validator.validate(user, errors);

        String frenchErrorMessage = messageSource.getMessage(errors.getFieldError("userName").getCode(), null, Locale.FRENCH);
        assertEquals("Le nom d'utilisateur ne peut pas être vide.", frenchErrorMessage);
    }
}