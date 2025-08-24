package com.example.springbootdemo.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;
  private Integer age;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Address address;

  public UserEntity(String name, Integer age) {
    this.name = name;
    this.age = age;
  }
}
