package springmvcdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/views")
public class ViewDemoController {
  @GetMapping()
  public String thymeleafExample(Model model) {
    model.addAttribute("message", "This is a message from a Spring controller rendered by a view");
    return "hi";
  }

  @GetMapping("/redirect")
  public String redirectToGoogle() {
    return "redirect:https://www.google.com";
  }

  @GetMapping("/forward")
  public String forwardExample() {
    return "forward:/views";
  }
}
