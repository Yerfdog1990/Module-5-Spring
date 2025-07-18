package spring_validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        // Validation logic
        // First, we want to make sure that the username is not empty
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "userName.empty", "Username cannot be empty");
        // Check that the email is not empty
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.empty", "Email cannot be empty");
        // Check that the email is correct
        String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!user.getEmail().matches(EMAIL_PATTERN)) {
            errors.rejectValue("email", "email.invalid", "Invalid email format");
        }
    }
}
