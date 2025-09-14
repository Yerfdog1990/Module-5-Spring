package oauth.socialapplication.service;

import oauth.socialapplication.dto.FacebookSignedRequest;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class FacebookDataDeletionService {

    @Value("${spring.security.oauth2.client.registration.facebook.client-secret}")
    private String appSecret;

    public Map<String, String> processDeletionRequest(String signedRequest) {
        try {
            FacebookSignedRequest request = parseSignedRequest(signedRequest);
            if (request != null) {
                // Here you would typically queue the deletion request in your system
                // and return a status URL and confirmation code
                String confirmationCode = UUID.randomUUID().toString();
                String statusUrl = "http://localhost:8080/deletion-status?code=" + confirmationCode;
                
                // Store the deletion request in your database with the confirmation code
                // deletionRequestRepository.save(new DeletionRequest(confirmationCode, request.getUserId()));
                
                Map<String, String> response = new HashMap<>();
                response.put("url", statusUrl);
                response.put("confirmation_code", confirmationCode);
                return response;
            }
        } catch (Exception e) {
            // Log the error
            e.printStackTrace();
        }
        throw new RuntimeException("Invalid request");
    }

    private FacebookSignedRequest parseSignedRequest(String signedRequest) throws NoSuchAlgorithmException, InvalidKeyException {
        String[] signedRequestParts = signedRequest.split("\\.", 2);
        if (signedRequestParts.length != 2) {
            return null;
        }

        String encodedSig = signedRequestParts[0];
        String payload = signedRequestParts[1];

        // Decode the data
        String sig = base64UrlDecode(encodedSig);
        String data = base64UrlDecode(payload);

        // Verify the signature
        if (verifySignature(payload, sig)) {
            // Parse the JSON data into FacebookSignedRequest object
            // You'll need to add Jackson or Gson to your dependencies if not already present
            try {
                com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
                return mapper.readValue(data, FacebookSignedRequest.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private boolean verifySignature(String payload, String expectedSig) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec key = new SecretKeySpec(appSecret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(key);
        byte[] signature = mac.doFinal(payload.getBytes(StandardCharsets.UTF_8));
        String actualSig = new String(signature, StandardCharsets.UTF_8);
        return actualSig.equals(expectedSig);
    }

    private String base64UrlDecode(String input) {
        // Replace URL-safe characters
        String base64 = input.replace('-', '+').replace('_', '/');
        // Add padding if needed
        switch (base64.length() % 4) {
            case 0: break;
            case 2: base64 += "=="; break;
            case 3: base64 += "="; break;
            default: throw new IllegalArgumentException("Illegal base64url string!");
        }
        return new String(Base64.decodeBase64(base64), StandardCharsets.UTF_8);
    }
}
