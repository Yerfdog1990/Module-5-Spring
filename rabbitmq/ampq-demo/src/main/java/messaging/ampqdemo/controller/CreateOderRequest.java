package messaging.ampqdemo.controller;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateOderRequest {
    @NotBlank
    private String orderId;

    @NotBlank
    @Email
    private String customerEmail;

    @Min(1)
    private double amount;
}
