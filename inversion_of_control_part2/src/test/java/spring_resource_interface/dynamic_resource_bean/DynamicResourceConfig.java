package spring_resource_interface.dynamic_resource_bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

@Configuration
class DynamicResourceConfig {
    @Bean
    public DynamicResourceBean dynamicResourceBean(ResourceLoader resourceLoader) {
        return new DynamicResourceBean(resourceLoader);
    }
}
