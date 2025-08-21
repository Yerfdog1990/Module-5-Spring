package springweb.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

  @GetMapping("/views")
  public String sanityCheck(Model model) {
    model.addAttribute("message", "Hello World from Thymeleaf!");
    LocalDateTime now = LocalDateTime.now();
    String formattedCurrentDateTime =
        now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    model.addAttribute("dateAndTime", formattedCurrentDateTime);
    return "check";
  }
}
