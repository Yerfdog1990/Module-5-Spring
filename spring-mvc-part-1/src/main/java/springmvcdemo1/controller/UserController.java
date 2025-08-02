package springmvcdemo1.controller;

import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

/**
 * Controller class handling user-related HTTP requests.
 * This class demonstrates basic Spring MVC REST endpoints.
 */
@RestController
// Indicates that this class is a REST controller that handles HTTP requests and automatically serializes responses
@RequestMapping("/user") // Maps all endpoints in this controller to start with /user path
public class UserController {
    private final MessageSource messageSource;
    public UserController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    /**
     * Handles GET requests to /user/greeting endpoint.
     *
     * @return A simple greeting message as plain text
     */
    @GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    public String sayHi() {
        return "Hi!";
    }
    // Maps HTTP GET requests to this method and specifies a response content type
    @RequestMapping(method = RequestMethod.GET, path = "/get-greeting", produces = MediaType.TEXT_PLAIN_VALUE)
    public String greetingFromGetRequest() {
        return "Hello World from get request";
    }
    @RequestMapping(method = RequestMethod.POST, path = "/post-greeting", produces = MediaType.TEXT_PLAIN_VALUE)
    public String greetingFromPostRequest(){
        return "Hello World from post request";
    }
    @GetMapping(value = "/static-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser(){
        return new User("John Doe",23, "Tired");
    }
    @PostMapping("/processed-user")
    public User processUser(@RequestBody User user){
        return new User(
                user.getName()+ " processed",
                user.getAge()+1,
                "very " +user.getMaritalStatus()
        );
    }
    @GetMapping("/create-user")
    public User createUser(@RequestParam String name, @RequestParam int age, @RequestParam String maritalStatus){
        return new User(name, age, maritalStatus);
    }
    @GetMapping("/i18n")
    public String greetFromBundle(){
        return messageSource.getMessage("greeting", null, new Locale("es"));
    }
    @GetMapping("/i18n/{lang}")
    public String greetFromBundle(@RequestHeader("Accept-Language") String lang){
        return messageSource.getMessage("greeting", null, new Locale(lang));
    }
}
