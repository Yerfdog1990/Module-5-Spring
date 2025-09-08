package messaging.ampqdemo.service;

import lombok.extern.slf4j.Slf4j;
import messaging.ampqdemo.configuration.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class OrderEventPublisher {
    private final RabbitTemplate rabbitTemplate;
    public OrderEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishOrderEvent(OrderCreatedEvent orderEvent) {
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE, RabbitConfig.ROUTING_KEY, orderEvent);
        log.info("Published order event: {}", orderEvent);
    }
}
