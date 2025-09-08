package messaging.ampqdemo.service;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Data
public class OrderCreatedEvent implements Serializable {
    private final String eventId;
    private final String orderId;
    private final String customerEmail;
    private final double amount;
    private final Instant occurredAt;


    public OrderCreatedEvent(String orderId, String customerEmail, double amount){
        eventId = UUID.randomUUID().toString();
        occurredAt = Instant.now();
        this.orderId = orderId;
        this.customerEmail = customerEmail;
        this.amount = amount;
    }
}
