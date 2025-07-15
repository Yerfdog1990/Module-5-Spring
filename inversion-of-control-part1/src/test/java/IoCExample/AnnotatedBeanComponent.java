package IoCExample;

import org.springframework.stereotype.Component;

@Component
public class AnnotatedBeanComponent {
    public <T> T echo(T msg) {
        return msg;
    }
}
