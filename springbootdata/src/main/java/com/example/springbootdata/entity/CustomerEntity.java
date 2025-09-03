package com.example.springbootdata.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customers")
@Data

public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private Integer age;

    // Constructors
    public CustomerEntity() {
    }
    public CustomerEntity(String name, String email, Integer age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public CustomerEntity(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
