package spring_resource_interface.static_resource_bean;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringJUnitConfig
public class StaticResourceBeanTest {
    @Autowired
    private StaticResourceBean staticResourceBean;

    @Test
    void loadStaticResourceAsDependency() throws Exception {
        String resourceTemplate = staticResourceBean.getResourceTemplate();
        assertNotNull(resourceTemplate);
        assertEquals("Hello world!", resourceTemplate.trim());
        System.out.println(resourceTemplate);
    }

    @Configuration
    static class ResourceAsDependency {
        @Bean
        public StaticResourceBean staticResourceBean(ResourceLoader resourceLoader) {
            return new StaticResourceBean(resourceLoader);
        }
    }
}
