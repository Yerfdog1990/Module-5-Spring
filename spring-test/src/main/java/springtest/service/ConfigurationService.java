package springtest.service;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationService {
    private final Environment environment;

    public ConfigurationService(Environment environment) {
        this.environment = environment;
    }

    public void printAppConfig() {
        String name = environment.getProperty("app.name");
        String version = environment.getProperty("app.version");
        System.out.printf("App name: %s%n", name);
        System.out.printf("App version: %s%n", version);

        System.out.printf("Java Version: %s%n", environment.getProperty("java.version", "Unknown"));
    }
}
