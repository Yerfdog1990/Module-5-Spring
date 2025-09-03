package com.example.springbootdata;

import com.example.springbootdata.entity.CustomerEntity;
import com.example.springbootdata.respository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SpringbootdataApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootdataApplication.class, args);
    }
    private static <T> void printList(List<T> list){
        list.forEach(System.out::println);
    }
    @Bean
    public CommandLineRunner queryDatabase(CustomerRepository repository){
        return args -> {
            // List all customers
            List<CustomerEntity> allCustomers = repository.findAll();
            printList(allCustomers);

            // Find a customer by email
            String email = "jane@example.com";
            List<CustomerEntity> customers = repository.findByEmail(email);
            printList(customers);

            // Find a customer by name
            String name = "John";
            List<CustomerEntity> customersByName = repository.findByNameNot(name);
            printList(customersByName);

            // Find a customer by age
            int age = 20;
            List<CustomerEntity> customersByAge = repository.findCustomersByAgeGreaterThan(age);
            printList(customersByAge);
        };
    }
    //@Bean
    public Boolean initDb(CustomerRepository repository){
        repository.save(new CustomerEntity("John", "john@example.com", 20));
        repository.save(new CustomerEntity("Jane", "jane@example.com", 21));
        repository.save(new CustomerEntity("Joe", "william@example.com", 22));
        return true;
    }
}
