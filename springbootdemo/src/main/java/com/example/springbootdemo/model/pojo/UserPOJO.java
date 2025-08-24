package com.example.springbootdemo.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPOJO {
  private Integer id;
  private String name;
  private Integer age;
}
