package springmvcdemo.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/greeting")
public class HelloController {

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView sayHello() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("name", "John Doe");
        modelAndView.addObject("age", 23);
        modelAndView.addObject("maritalStatus", "Tired");
        return modelAndView;
    }
}
