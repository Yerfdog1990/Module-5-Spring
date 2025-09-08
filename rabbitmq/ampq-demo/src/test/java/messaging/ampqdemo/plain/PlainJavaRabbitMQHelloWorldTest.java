package messaging.ampqdemo.plain;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Testcontainers
public class PlainJavaRabbitMQHelloWorldTest {

    @Container
    static RabbitMQContainer rabbitMQContainer = new RabbitMQContainer("rabbitmq:3.7.25-management-alpine");

    @Test
    void sendAndReceiveMessage() {
        String queueName = "hello";
        String message = "Hello RabbitMQ!";

        // Create a connection to the RabbitMQ broker
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(rabbitMQContainer.getHost());
        factory.setPort(rabbitMQContainer.getAmqpPort());

        try(Connection connection = factory.newConnection()) {
            Channel channel = connection.createChannel();

            // Declare a queue
            channel.queueDeclare(queueName, false, false, false, null);

            // Publish the message
            Charset charset = StandardCharsets.UTF_8;
            channel.basicPublish("", queueName, null, message.getBytes(charset));

            // Consume the message
            GetResponse getResponse = channel.basicGet(queueName, true);
            String messageReceived = new String(getResponse.getBody(), charset);
            assertThat(messageReceived).isEqualTo(message);
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}
