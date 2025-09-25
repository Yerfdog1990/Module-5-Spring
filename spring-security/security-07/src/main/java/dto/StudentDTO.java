package dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDTO {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String role;
}
