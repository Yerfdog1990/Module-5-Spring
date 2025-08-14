package springtest.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Getter
public class MyInjectingPropertiesService {
    @Value("${my.property:thisIsTheDefaultValue}")
    private String myProperty;
}
