package springweb.model.service;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
  private Integer id;
  private String name;
  private Integer age;

  public UserDTO(Integer id, String name, Integer age) {
    this.id = id;
    this.name = name;
    this.age = age;
  }

  // Lombok's @Data will generate all getters, setters, toString, equals, and hashCode
}
