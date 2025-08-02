package springmvcdemo1.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

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
    @GetMapping("param/{name}")
    public ModelAndView greetParametric(ModelAndView modelAndView, @PathVariable String name){
        modelAndView.setViewName("index");
        modelAndView.addObject("name", name);
        modelAndView.addObject("age", 23);
        modelAndView.addObject("maritalStatus", "Tired");
        return modelAndView;
    }
    @GetMapping("/regex/{name:[a-z]+}")
    public ModelAndView greetRegularExpression(ModelAndView modelAndView, @PathVariable String name){
        modelAndView.setViewName("index");
        modelAndView.addObject("name", name);
        modelAndView.addObject("age", 23);
        modelAndView.addObject("maritalStatus", "Tired");
        return modelAndView;
    }
    @GetMapping(path="/param/{name:[a-z]+}", params ="myName=Godfrey")
    public ModelAndView greetParamValue(ModelAndView modelAndView, @PathVariable String name){
        modelAndView.setViewName("index");
        modelAndView.addObject("name", name);
        modelAndView.addObject("age", 23);
        modelAndView.addObject("maritalStatus", "Tired");
        return modelAndView;
    }
    @GetMapping("/{name}/greeting")
    public ModelAndView greetOptional(ModelAndView modelAndView, @RequestParam Optional<String> name){
        modelAndView.setViewName("index");
        modelAndView.addObject("name", name.orElse("Visitor"));
        modelAndView.addObject("age", 23);
        modelAndView.addObject("maritalStatus", "Tired");
        return modelAndView;
    }
    @GetMapping(path = "/http-request", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String captureHttpRequest(HttpServletRequest request, HttpSession session){

        return request.getHeader("host");
    }
    @GetMapping(path = "/http-session", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String captureHttpSession(HttpSession session){
        return session.getId();
    }
}
