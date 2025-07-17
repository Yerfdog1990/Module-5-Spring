package spring_resource_interface.static_resource_bean;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.nio.charset.Charset;

public class StaticResourceBean {
    private final Resource template;
    public StaticResourceBean(ResourceLoader resourceLoader) {
        this.template = resourceLoader.getResource("classpath:static-template.txt");
    }
    public String getResourceTemplate() throws IOException {
        return template.getContentAsString(Charset.defaultCharset());
    }
}
