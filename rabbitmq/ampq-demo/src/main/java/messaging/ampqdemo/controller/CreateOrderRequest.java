package messaging.ampqdemo.controller;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {
    @NotBlank
    private String orderId;

    @NotBlank
    @Email
    private String customerEmail;

    @NotNull
    @Positive
    private Double amount;
}
