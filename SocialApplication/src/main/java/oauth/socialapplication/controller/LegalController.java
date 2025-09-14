package oauth.socialapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LegalController {
    @GetMapping("/privacy-policy")
    public String privacyPolicy() {
        return "privacy-policy";
    }

    @GetMapping("/terms-of-service")
    public String termsOfService() {
        return "terms-of-service";
    }
    
    @GetMapping("/data-deletion")
    public String dataDeletion() {
        return "data-deletion";
    }
}