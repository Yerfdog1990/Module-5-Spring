package config;

import org.jspecify.annotations.NullMarked;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.filter.DelegatingFilterProxy;

import jakarta.servlet.Filter;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    
    @Override
    @NullMarked
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { SecurityConfig.class };
    }

    @Override
    @NullMarked
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebConfig.class };
    }

    @Override
    @NullMarked
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
    
    @Override
    @NullMarked
    protected Filter[] getServletFilters() {
        // Register the springSecurityFilterChain
        return new Filter[] { new DelegatingFilterProxy("springSecurityFilterChain") };
    }
    
    @Override
    @NullMarked
    protected boolean isAsyncSupported() {
        return true;
    }
}
