package springweb.controller;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewEntityResponse {
  private Integer id;

  public NewEntityResponse(Integer id) {
      this.id = id;
  }

  // Lombok's @Data will generate all getters, setters, toString, equals, and hashCode
}
