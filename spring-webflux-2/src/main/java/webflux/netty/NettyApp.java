package webflux.netty;

import java.util.function.BiFunction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;
import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServer;
import reactor.netty.http.server.HttpServerRequest;
import reactor.netty.http.server.HttpServerResponse;
import webflux.config.WebFluxConfig;

public class NettyApp {
  public static void main(String[] args) {
    // Obtain a spring application context
    ApplicationContext context = new AnnotationConfigApplicationContext(WebFluxConfig.class);

    // Build the HttpHandler for webflux
    HttpHandler handler = WebHttpHandlerBuilder.applicationContext(context).build();

    // Adapt the HttpHandler from spring to netty
    BiFunction<HttpServerRequest, HttpServerResponse, Mono<Void>> adapter =
        new ReactorHttpHandlerAdapter(handler);

    // Create a netty server and bind it to localhost:8080
    HttpServer.create().port(8080).handle(adapter).bindNow().onDispose().block();
  }
}
