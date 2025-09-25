package controller;

import dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserDetailsServiceImpl;

@Controller
public class RegistrationController {
    private final UserDetailsServiceImpl userDetailsService;
    
    @Autowired
    public RegistrationController(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/register")
    public String showRegistrationPage(@ModelAttribute("registrationDTO") StudentDTO dto) {
        return "register";
    }

    @PostMapping("/registered-user")
    @ResponseBody
    public String processRegistration(StudentDTO dto) {
        // normalize/auto-generate email same as createUser
        String effectiveEmail = (dto.getEmail() == null || dto.getEmail().trim().isEmpty())
                ? (dto.getUsername() + "@example.com")
                : dto.getEmail();
        effectiveEmail = effectiveEmail.toLowerCase();
        try {
            // Check if user exists by email (case-insensitive in service)
            userDetailsService.loadUserByUsername(effectiveEmail);
            return "User with the email " + effectiveEmail + " already exists.";
        } catch (UsernameNotFoundException e) {
            // Create new user if not exists
            userDetailsService.createUser(dto);
            return "Registration successful for " + dto.getUsername();
        }
    }
}
