package spring_resource_interface.dynamic_resource_bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class DynamicResourceBean {
    private final ResourceLoader resourceLoader;
    private final Map<String, String> roleToTemplateMap = new HashMap<>();

    public DynamicResourceBean(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
        roleToTemplateMap.put("Admin", "classpath:admin-template.txt");
        roleToTemplateMap.put("User", "classpath:user-template.txt");
    }
    public String getTemplateForRole(String role) throws IOException {
        String templatePath = roleToTemplateMap.get(role);
        if(templatePath != null){
            return resourceLoader.getResource(templatePath).getContentAsString(Charset.defaultCharset());
        } else {
            throw new IllegalArgumentException("No template found for role: " + role);
        }
    }
}
