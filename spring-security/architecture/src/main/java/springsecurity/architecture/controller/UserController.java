package springsecurity.architecture.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/public/user")
    public String publicHello(){
        return "<h1>Hello public user!</h1>";
    }
    @GetMapping("/me")
    public String me(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated()){
            return "No authenticated user";
        }
        return "Logged in as: " + authentication.getName() +
                " roles: " + authentication.getAuthorities();
    }
}
