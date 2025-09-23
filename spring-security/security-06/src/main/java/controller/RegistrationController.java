package controller;

import dto.RegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegistrationController {
    private final JdbcUserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public RegistrationController(JdbcUserDetailsManager userDetailsManager, PasswordEncoder passwordEncoder) {
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }

//    @GetMapping("/register")
//    public String showRegistrationPage(Model model) {
//        RegistrationDTO registrationDTO = new RegistrationDTO();
//        model.addAttribute("registrationDTO", registrationDTO);
//        return "register";
//    }

    @GetMapping("/register")
    public String showRegistrationPage(@ModelAttribute("registrationDTO") RegistrationDTO dto) {
        return "register";
    }

    @PostMapping("/registered-user")
    @ResponseBody
    public String processRegistration(RegistrationDTO dto){
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        UserDetails appUser = User
                .withUsername(dto.getUsername())
                .password(encodedPassword)
                .roles("USER")
                .build();
        userDetailsManager.createUser(appUser);
        return "Registration successful for " + dto.getUsername();
    }
}
