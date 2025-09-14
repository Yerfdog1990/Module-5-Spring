package oauth.socialapplication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FacebookSignedRequest {
    private String algorithm;
    private Long expires;
    @JsonProperty("issued_at")
    private Long issuedAt;
    @JsonProperty("user_id")
    private String userId;
    
    // Add any other fields that might be in the signed request
}
