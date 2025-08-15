package reactordemo;

import reactor.core.publisher.Mono;
import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServer;

public class ReactorHelloWorld {
    public static void main(String[] args){
        HttpServer server = HttpServer.create().port(8080).route(httpServerRoutes -> {
            httpServerRoutes.get("/hello", (request, response) -> response.sendString(Mono.just("Hello World!")));
            httpServerRoutes.get("/goodbye", (request, response) -> response.sendString(Mono.just("Goodbye World!")));
        });
        DisposableServer disposableServer = server.bindNow();
        disposableServer.onDispose().block();
    }
}
