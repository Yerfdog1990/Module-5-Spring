package com.example.springbootdata.respository;

import com.example.springbootdata.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
    List<CustomerEntity> findByEmail(String email);

    List<CustomerEntity> findByNameNot(String name);

    @Query("SELECT c FROM CustomerEntity c WHERE c.age > :age")
    List<CustomerEntity> findCustomersByAgeGreaterThan(int age);
}
