package webflux.undertow;

import io.undertow.Undertow;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.UndertowHttpHandlerAdapter;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;
import webflux.config.WebFluxConfig;

public class UndertowApp {
  public static void main(String[] args) {
    // Get a spring application context
    ApplicationContext context = new AnnotationConfigApplicationContext(WebFluxConfig.class);

    // Build the HttpHandler for webflux
    HttpHandler handler = WebHttpHandlerBuilder.applicationContext(context).build();

    // Adapt the HttpHandler from spring to undertow
    UndertowHttpHandlerAdapter adapter = new UndertowHttpHandlerAdapter(handler);

    // Create an undertow server and bind it to localhost:8080
    Undertow.builder().addHttpListener(8080, "localhost").setHandler(adapter).build().start();
  }
}
