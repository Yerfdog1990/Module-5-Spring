package spring_resource_interface.dynamic_resource_bean;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(classes = DynamicResourceConfig.class)
public class DynamicResourceTest {
    @Autowired
    public DynamicResourceBean dynamicResourceBean;

    @Test
    void loadAdminDynamicResourceAsDependency() throws Exception {
        String template = dynamicResourceBean.getTemplateForRole("Admin");
        System.out.println(template);
        assert template.contains("Hello, I am the admin.");
    }
    @Test
    void loadUserDynamicResourceAsDependency() throws Exception {
        String template = dynamicResourceBean.getTemplateForRole("User");
        System.out.println(template);
        assert template.contains("Hello, I am the user.");
    }
}
