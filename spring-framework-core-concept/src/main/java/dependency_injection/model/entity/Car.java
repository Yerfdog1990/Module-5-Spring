package dependency_injection.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Data
@NoArgsConstructor
public class Car{
    private int engine;
    private int year;
    private String color;
    private String model;
}
