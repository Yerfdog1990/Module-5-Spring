package messaging.ampqdemo.controller;

import messaging.ampqdemo.service.OrderCreatedEvent;
import messaging.ampqdemo.service.OrderCreatedListener;
import messaging.ampqdemo.service.OrderEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderEventPublisher orderEventPublisher;
    private final OrderCreatedListener orderCreatedListener;
    public OrderController(OrderEventPublisher orderEventPublisher, OrderCreatedListener orderCreatedListener) {
        this.orderEventPublisher = orderEventPublisher;
        this.orderCreatedListener = orderCreatedListener;
    }

    @PostMapping
    public ResponseEntity<CreateOrderResponse> postOrder(CreateOderRequest request){
        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent(request.getOrderId(), request.getCustomerEmail(), request.getAmount());
        orderEventPublisher.publishOrderEvent(orderCreatedEvent);
        return ResponseEntity.accepted().body(new CreateOrderResponse("ACCEPTED", "Order created successfully"));
    }

    @GetMapping
    public Map<String, Double> list() {
        return orderCreatedListener.currentOrder();
    }
}
