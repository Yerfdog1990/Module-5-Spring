package springtest.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MyWebClientService {
    private final RestTemplate restTemplate;

    public MyWebClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getDataFromExternalService(String url) {
        return restTemplate.getForObject(url, String.class);
    }
}
