package model.entity;

import lombok.Value;

@Value
public class User {
  private String username;
  private String password;
  private String email;
  private String role;
}
