package springmvcdemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {
  @GetMapping(value = "/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
  public ResponseEntity<String> getStudentById(@PathVariable("id") Integer id) {
    if (id == 1) {
      return ResponseEntity.ok("Student found");
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student Not Found");
    }
  }

  @PostMapping
  public ResponseEntity<String> createStudent(@RequestBody String studentName) {
    return ResponseEntity.status(HttpStatus.CREATED).body("Student Created: " + studentName);
  }
}
