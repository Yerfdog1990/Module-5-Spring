package oauth.socialapplication.controller;

import oauth.socialapplication.service.FacebookDataDeletionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/facebook")
public class FacebookDataDeletionController {

    private final FacebookDataDeletionService deletionService;

    @Autowired
    public FacebookDataDeletionController(FacebookDataDeletionService deletionService) {
        this.deletionService = deletionService;
    }

    @PostMapping("/data-deletion")
    public ResponseEntity<Map<String, String>> handleDataDeletion(
            @RequestParam("signed_request") String signedRequest) {
        try {
            Map<String, String> response = deletionService.processDeletionRequest(signedRequest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping("/deletion-status")
    public String checkDeletionStatus(@RequestParam("code") String confirmationCode) {
        // In a real application, you would look up the status using the confirmation code
        // For now, we'll return a simple status page
        return "<html>" +
               "<head><title>Data Deletion Status</title>" +
               "<link rel=\"stylesheet\" href=\"/webjars/bootstrap/css/bootstrap.min.css\"></head>" +
               "<body class=\"container mt-5\">" +
               "<div class=\"card\">" +
               "<div class=\"card-body\">" +
               "<h1>Data Deletion Request Status</h1>" +
               "<p>Your data deletion request has been received and is being processed.</p>" +
               "<p>Confirmation Code: " + confirmationCode + "</p>" +
               "<p>We will process your request within 30 days as required by Facebook's policies.</p>" +
               "<a href=\"/\" class=\"btn btn-primary\">Return to Home</a>" +
               "</div></div></body></html>";
    }
}
