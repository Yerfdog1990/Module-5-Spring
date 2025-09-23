package config;

import org.jspecify.annotations.NullMarked;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    @NullMarked
    protected Class<?> [] getRootConfigClasses() {
        return null;
    }

    @Override
    @NullMarked
    protected Class<?> [] getServletConfigClasses() {
        return new Class[]{WebConfig.class, SecurityConfig.class};
    }

    @Override
    @NullMarked
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
