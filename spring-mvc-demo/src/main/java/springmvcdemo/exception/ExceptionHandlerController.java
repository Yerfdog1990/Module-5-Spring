package springmvcdemo.exception;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exception-handler")
public class ExceptionHandlerController {

  @GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
  public String home() {
    throw new ArithmeticException("I've been offended!");
  }

  @GetMapping(produces = MediaType.TEXT_PLAIN_VALUE, path = "/illegal-argument")
  public String livingRoom() {
    throw new IllegalArgumentException("This argument also offended me!");
  }

//  @ExceptionHandler
//  public ResponseEntity<String> handleArithmeticException(ArithmeticException ex) {
//    return ResponseEntity.badRequest().body(ex.getMessage());
//  }
}
