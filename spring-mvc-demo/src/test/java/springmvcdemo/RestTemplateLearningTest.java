package springmvcdemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class RestTemplateLearningTest {
  private final RestTemplate restTemplate = new RestTemplate();

  @Test
  public void testGetForObjectAsString() {
    // Simple GET Request
    String url = "https://jsonplaceholder.typicode.com/todos/1";
    String responseAsString = restTemplate.getForObject(url, String.class);
    System.out.println("GET response: " + responseAsString);

    assertTrue(Objects.requireNonNull(responseAsString).contains("userId"));
  }

  @Test
  public void testGetForObjectAsPOJO() {
    String url = "https://jsonplaceholder.typicode.com/todos/1";
    ResponseMessage responseAsPOJO = restTemplate.getForObject(url, ResponseMessage.class);

    System.out.println(responseAsPOJO);
    ResponseMessage expectedResponse = new ResponseMessage(1, 1, "delectus aut autem", false);
    assertEquals(expectedResponse, responseAsPOJO);
  }

  @Test
  public void testGetForEntityString() {
    String url = "https://jsonplaceholder.typicode.com/todos/1";
    ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

    assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
    assertTrue(responseEntity.getBody().contains("userId"));
  }

  @Test
  public void testGetForEntityPojo() {
    String url = "https://jsonplaceholder.typicode.com/todos/1";
    ResponseEntity<ResponseMessage> responseEntity =
        restTemplate.getForEntity(url, ResponseMessage.class);

    assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
    ResponseMessage expectedResponse = new ResponseMessage(1, 1, "delectus aut autem", false);
    ResponseMessage responseAsPOJO = responseEntity.getBody();
    assertEquals(expectedResponse, responseAsPOJO);
  }

  @Test
  public void testPostForObject() {
    String url = "https://jsonplaceholder.typicode.com/posts";

    String requestBody =
"""
    {"title": "foo", "body": "bar", "userId": 1}
""";

    String responseAsString = restTemplate.postForObject(url, requestBody, String.class);
    System.out.println(responseAsString);
  }

  @Test
  public void testExchange() {
    String url = "https://jsonplaceholder.typicode.com/posts";

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    String requestBody =
        """
            {"title": "foo", "body": "bar", "userId": 1}
        """;

    HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

    ResponseEntity<PostResponse> responseEntity =
        restTemplate.exchange(url, HttpMethod.POST, entity, PostResponse.class);

    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    assertEquals(101, responseEntity.getBody().getId());
  }
}
