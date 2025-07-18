package spring_validation;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class ValidationTestConfig {
    @Bean
    public MessageSource messageSource(){
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        // Set the base name for the properties file without the extension
        messageSource.setBasenames("messages"); // matches messages.properties filename
        // Set the default encoding
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}