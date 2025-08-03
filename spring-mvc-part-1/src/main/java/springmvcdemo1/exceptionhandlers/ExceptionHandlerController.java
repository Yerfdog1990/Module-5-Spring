package springmvcdemo1.exceptionhandlers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequestMapping("/exception")
public class ExceptionHandlerController {
    @GetMapping(value = "/arithmetic", produces = MediaType.TEXT_PLAIN_VALUE)
    public String arithmeticException(){
        throw new ArithmeticException("Throwing arithmetic exception. Something went wrong!");
    }
    @GetMapping(value = "/illegal-argument", produces = MediaType.TEXT_PLAIN_VALUE)
    public String illegalArgumentException(){
        throw new IllegalArgumentException("Throwing illegal argument exception. Something went wrong!");
    }
//    @ExceptionHandler(ArithmeticException.class)
//    public ResponseEntity<String> handleArithmeticException(ArithmeticException e){
//        return ResponseEntity.badRequest().body(e.getMessage());
//    }
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<String> handleException(IllegalArgumentException e){
//        return ResponseEntity.badRequest().body(e.getMessage());
//    }
}
