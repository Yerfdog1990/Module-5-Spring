package messaging.ampqdemo.service;

import lombok.extern.slf4j.Slf4j;
import messaging.ampqdemo.configuration.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class OrderCreatedListener {
    private final Map<String, Double> readModel = new ConcurrentHashMap<>();

    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void onOrderCreated(@Payload OrderCreatedEvent event){
        if (event == null) {
            log.warn("Received null OrderCreatedEvent payload; ignoring message.");
            return;
        }
        String orderId = event.getOrderId();
        Double amount = event.getAmount();

        if (orderId == null || orderId.isBlank()) {
            log.warn("Received OrderCreatedEvent with null/blank orderId; event={}, ignoring message.", event);
            return;
        }

        readModel.put(orderId, amount);
        log.info("Read model updated: {}", readModel);
    }
    public Map<String, Double> currentOrder() {
        return readModel;
    }
}