package config;

import org.jspecify.annotations.NullMarked;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import org.springframework.lang.NonNull;


public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    @NullMarked
    protected Class<?>[] getRootConfigClasses() {
        // No root (parent) context. Load everything into the DispatcherServlet's context
        return null;
    }

    @Override
    @NullMarked
    protected Class<?>[] getServletConfigClasses() {
        // Register both MVC and Security in the DispatcherServlet's application context
        return new Class<?>[] { WebConfig.class, SecurityConfig.class };
    }

    @Override
    @NullMarked
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
    }
