package com.example.modulithdemo.tasks;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.modulith.test.ApplicationModuleTest;

@ApplicationModuleTest
public class TaskModuleTest {
    @Autowired
    TaskService taskService;

    @Test
    void testDependencies() {
        String greeting = "Hello from TaskService!";
        String hello = taskService.greet(greeting);
        Assertions.assertThat(greeting).isEqualTo("Hello from TaskService!");
    }
}
