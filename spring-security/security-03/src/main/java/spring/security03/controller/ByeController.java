package spring.security03.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ByeController {
    @GetMapping("/")
    public String publicHello(){
        return "<h1>Hello public user!</h1>";
    }
    @GetMapping("/bye")
    public String sayBye(){
        return "Bye everyone!";
    }
}
