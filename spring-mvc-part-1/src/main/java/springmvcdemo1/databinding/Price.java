package springmvcdemo1.databinding;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Price {
    private double amount;
    private String currency;
}
