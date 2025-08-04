package springmvcdemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content-negotiation")
public class ContentNegotiationController {
  @GetMapping(
      path = "/message",
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_HTML_VALUE})
  public ResponseEntity<?> getMessage(@RequestHeader("Accept") String acceptHeader) {
    if (acceptHeader.contains("json")) {
      return ResponseEntity.status(HttpStatus.OK)
          .contentType(MediaType.APPLICATION_JSON)
          .body("{\"message\":\"Hello, JSON!\"}");
    } else {
      return ResponseEntity.status(HttpStatus.OK)
          .contentType(MediaType.TEXT_HTML)
          .body("<h1>Hello, HTML!</h1>");
    }
  }
}
