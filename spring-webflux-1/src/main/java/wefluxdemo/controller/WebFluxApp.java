package wefluxdemo.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;
import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServer;
import reactor.netty.http.server.HttpServerRequest;
import reactor.netty.http.server.HttpServerResponse;
import wefluxdemo.config.WebFluxConfig;

import java.util.function.BiFunction;

@RestController
public class WebFluxApp {
  public static void main(String[] args){
      // Obtain a spring application context
      ApplicationContext ctx = new AnnotationConfigApplicationContext(WebFluxConfig.class);

      // Build the HttpHandler from the spring application context
      HttpHandler httpHandler = WebHttpHandlerBuilder.applicationContext(ctx).build();

      // Adapt the HttpHandler interface so it can be used by a server(e.g. Netty, Tomcat, Jetty, Undertow)
      BiFunction<HttpServerRequest, HttpServerResponse, Mono<Void>> adapter = new ReactorHttpHandlerAdapter(httpHandler);

      HttpServer.create().port(8080).handle(adapter).bindNow().onDispose().block();
  }
}
