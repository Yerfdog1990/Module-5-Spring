package springmvcdemo;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
// @EnableWebMvc
public class AppConfig {
  @Bean
  public ViewResolver jspViewResolver() {
    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setPrefix("/WEB-INF/views/");
    viewResolver.setSuffix(".jsp");
    return viewResolver;
  }
  
//  @Bean
//  public ITemplateResolver templateResolver() {
//    SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
//    templateResolver.setPrefix("/WEB-INF/templates/");
//    templateResolver.setSuffix(".html");
//    return templateResolver;
//  }
//
//  @Bean
//  public ISpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
//    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//    templateEngine.setTemplateResolver(templateResolver);
//    templateEngine.setEnableSpringELCompiler(true);
//    return templateEngine;
//  }
//
//  @Bean
//  public ViewResolver thymeleafViewResolver(ISpringTemplateEngine templateEngine) {
//    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//    viewResolver.setTemplateEngine(templateEngine);
//    return viewResolver;
//  }

  @Bean
  public MessageSource messageSource() {
    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
    messageSource.setBasename("classpath:messages");
    messageSource.setDefaultEncoding("UTF-8");
    return messageSource;
  }

  @Bean
  public HandlerMapping requestMapping() {
    return null;
  }
}
