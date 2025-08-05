package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplateController {

    @GetMapping("/thymeleaf")
    public String thymeleafPage(Model model) {
        model.addAttribute("message", "Hello Thymeleaf user!");
        return "index"; // index.html
    }

    @GetMapping("/freemarker")
    public String freemarkerPage(Model model) {
        model.addAttribute("message", "Hello Freemarker user!");
        return "index"; // index.ftl
    }

    @GetMapping("/mustache")
    public String mustachePage(Model model) {
        model.addAttribute("message", "Hello Mustache user!");
        return "index"; // index.mustache
    }

    @GetMapping("/groovy")
    public String groovyPage(Model model) {
        model.addAttribute("message", "Hello Groovy user!");
        return "index"; // index.tpl
    }
}
