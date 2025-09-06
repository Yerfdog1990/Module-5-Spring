package com.example.modulithdemo.tasks;

import com.example.modulithdemo.ModulithDemoApplication;
import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

public class ModularityTest {
    private final ApplicationModules modules = ApplicationModules.of(ModulithDemoApplication.class);

    @Test
    void verifyModuleBoundariesTest(){
        modules.verify();
    }
}
