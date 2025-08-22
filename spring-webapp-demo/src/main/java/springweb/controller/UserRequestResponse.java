package springweb.controller;

import lombok.Data;

@Data
public class UserRequestResponse {
  private Integer id;
  private String name;
  private Integer age;

  public UserRequestResponse() {}

  public UserRequestResponse(Integer id, String name, Integer age) {
    this.id = id;
    this.name = name;
    this.age = age;
  }

  // Lombok's @Data will generate all getters, setters, toString, equals, and hashCode
}
