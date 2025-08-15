package wefluxdemo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;

@Configuration
@ComponentScan(basePackages = "wefluxdemo")
@EnableWebFlux
public class WebFluxConfig {}
