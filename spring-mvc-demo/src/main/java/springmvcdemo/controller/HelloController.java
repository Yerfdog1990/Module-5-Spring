package springmvcdemo.controller;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
  //  @GetMapping(path = "/greet")
  //  public ModelAndView greet(ModelAndView modelAndView) {
  //    // ModelAndView modelAndView = new ModelAndView();
  //    modelAndView.setViewName("hello");
  //    modelAndView.addObject("name", "John Doe");
  //    modelAndView.addObject("age", 23);
  //    modelAndView.addObject("maritalStatus", "Tired");
  //    return modelAndView;
  //  }

  @GetMapping("/jane")
  public String greetAgain(ModelAndView modelAndView) {
    modelAndView.addObject("name", "Jane Doe");
    modelAndView.addObject("age", 25);
    modelAndView.addObject("maritalStatus", "Single");
    return "hello";
  }

  @GetMapping(path = "/greet/{age}")
  public ModelAndView greetParametric(
      ModelAndView modelAndView, @PathVariable int age) {
    modelAndView.setViewName("hello");
    modelAndView.addObject("name", "Visitor");
    modelAndView.addObject("age", age);
    modelAndView.addObject("maritalStatus", "Single");
    return modelAndView;
  }

  @GetMapping(path = "/capture", produces = MediaType.TEXT_PLAIN_VALUE)
  @ResponseBody
  public String captureThings(HttpServletRequest req, HttpSession session) {
    return session.getId();
  }

  @GetMapping(path = "/conflict", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<User> userConflict() {
    User user = new User("Joseph", 30, "married");

    return ResponseEntity.status(HttpStatus.CONFLICT)
        .header("X-metaverse-realm", "Minecraft")
        .body(user);
  }
}
