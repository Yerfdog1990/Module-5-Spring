package springwebapp.config;

import org.springframework.lang.NonNull;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    
    @Override
    protected Class<?>[] getRootConfigClasses() {
        // No root (parent) context. Load everything into the DispatcherServlet's context
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        // Register both MVC and Security in the DispatcherServlet's application context
        return new Class<?>[] { WebConfig.class, SecurityConfig.class };
    }

    @Override
    @NonNull
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

}
