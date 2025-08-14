package reactordemo;

import reactor.core.publisher.Mono;
import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServer;

public class ReactorHelloWorld {
    public static void main(String[] args) {
        HttpServer.create().port(8080).route(httpServerRoutes ->{ httpServerRoutes.get("/hello", (httpServerRequest, httpServerResponse) -> httpServerResponse.sendString(Mono.just("Hello World!")));
            httpServerRoutes.get("/hello/{name}", (httpServerRequest, httpServerResponse) -> httpServerResponse.sendString(Mono.just("Hello " + httpServerRequest.param("name"))));
        });
        DisposableServer disposableServer = HttpServer.create().bindNow();
        disposableServer.onDispose().block();
    }
}
