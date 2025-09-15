package com.codegym.jira.profile.internal.web;

import com.codegym.jira.login.AuthUser;
import com.codegym.jira.login.Role;
import com.codegym.jira.login.User;
import com.codegym.jira.profile.ProfileTo;
import com.codegym.jira.profile.internal.ProfileMapper;
import com.codegym.jira.profile.internal.ProfileRepository;
import com.codegym.jira.profile.internal.model.Profile;
import com.codegym.jira.ref.RefTo;
import com.codegym.jira.ref.RefType;
import com.codegym.jira.ref.ReferenceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.Matchers.hasKey;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ProfileRestController.class)
@Import({com.codegym.jira.common.error.ErrorMessageHandler.class, com.codegym.jira.config.TestMvcConfig.class, com.codegym.jira.config.TestSecurityConfig.class})
class ProfileRestControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    private static final String REST_URL = ProfileRestController.REST_URL;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfileMapper profileMapper;

    @MockBean
    private ProfileRepository profileRepository;

    private AuthUser authUser;

    @BeforeEach
    void setUp() {
        // Initialize static reference data for ReferenceService used in ProfileUtil
        Map<RefType, Map<String, RefTo>> refSelect = new LinkedHashMap<>();
        Map<String, RefTo> mail = new LinkedHashMap<>();
        mail.put("assigned", new RefTo(1L, RefType.MAIL_NOTIFICATION, "assigned", "Assigned", "1"));
        mail.put("overdue", new RefTo(2L, RefType.MAIL_NOTIFICATION, "overdue", "Overdue", "2"));
        mail.put("deadline", new RefTo(3L, RefType.MAIL_NOTIFICATION, "deadline", "Deadline", "4"));
        mail.put("three_days_before_deadline", new RefTo(4L, RefType.MAIL_NOTIFICATION, "three_days_before_deadline", "3 days", "8"));
        mail.put("two_days_before_deadline", new RefTo(5L, RefType.MAIL_NOTIFICATION, "two_days_before_deadline", "2 days", "16"));
        mail.put("one_day_before_deadline", new RefTo(6L, RefType.MAIL_NOTIFICATION, "one_day_before_deadline", "1 day", "32"));
        Map<String, RefTo> contacts = new LinkedHashMap<>();
        for (String code : Set.of("skype", "mobile", "website", "github", "tg", "facebook", "linkedin")) {
            contacts.put(code, new RefTo(null, RefType.CONTACT, code, code, null));
        }
        refSelect.put(RefType.MAIL_NOTIFICATION, mail);
        refSelect.put(RefType.CONTACT, contacts);
        ReflectionTestUtils.setField(ReferenceService.class, "refSelect", refSelect);

        User user = new User(1L, "user@test.com", "password", "User", null, "User", Role.DEV);
        authUser = new AuthUser(user);
    }

    @Test
    @DisplayName("GET /api/profile - success")
    void get_success() throws Exception {
        long id = authUser.id();
        Profile profile = new Profile(id);
        ProfileTo to = ProfileTestData.USER_PROFILE_TO;

        given(profileRepository.getOrCreate(id)).willReturn(profile);
        given(profileMapper.toTo(profile)).willReturn(to);

        mockMvc.perform(get(REST_URL)
                        .with(authentication(new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(authUser, authUser.getPassword(), authUser.getAuthorities()))))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.mailNotifications").isArray())
                .andExpect(jsonPath("$.contacts").isArray());

        verify(profileRepository).getOrCreate(id);
        verify(profileMapper).toTo(profile);
    }

    @Test
    @DisplayName("GET /api/profile - unauthorized when no principal")
    void get_unauthorized() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("PUT /api/profile - success updates profile")
    void update_success() throws Exception {
        long id = authUser.id();
        ProfileTo updateTo = ProfileTestData.getUpdatedTo();
        updateTo.setId(id);
        
        // Create a mock profile that will be returned by the repository
        Profile mockProfile = new Profile();
        mockProfile.setId(id);
        Profile merged = new Profile(id);
        given(profileRepository.getOrCreate(id)).willReturn(mockProfile);
        given(profileMapper.updateFromTo(eq(mockProfile), any(ProfileTo.class))).willReturn(merged);
        given(profileRepository.save(merged)).willReturn(merged);

        mockMvc.perform(put(REST_URL)
                        .with(authentication(new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(authUser, authUser.getPassword(), authUser.getAuthorities())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateTo)))
                .andExpect(status().isNoContent());

        // Verify the interactions
        verify(profileRepository).getOrCreate(id);
        verify(profileMapper).updateFromTo(eq(mockProfile), ArgumentMatchers.<ProfileTo>any());
        verify(profileRepository).save(any(Profile.class));
    }

    @Test
    @DisplayName("PUT /api/profile - bad request when id mismatched with principal")
    void update_badRequest_onIdMismatch() throws Exception {
        long id = authUser.id();
        // Build a to with different id set explicitly (mismatch)
        ProfileTo updateTo = ProfileTestData.getUpdatedTo();
        updateTo.setId(id + 1);

        mockMvc.perform(put(REST_URL)
                        .with(authentication(new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(authUser, authUser.getPassword(), authUser.getAuthorities())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateTo)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().contentTypeCompatibleWith("application/problem+json"));
    }

    @Test
    @DisplayName("PUT /api/profile - bad request on bean validation errors")
    void update_badRequest_onBeanValidation() throws Exception {
        long id = authUser.id();
        ProfileTo invalid = ProfileTestData.getInvalidTo();

        mockMvc.perform(put(REST_URL)
                        .with(authentication(new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(authUser, authUser.getPassword(), authUser.getAuthorities())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalid)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().contentTypeCompatibleWith("application/problem+json"))
                .andExpect(jsonPath("$.invalid_params").exists())
                .andExpect(jsonPath("$.invalid_params", hasKey("mailNotifications[]")));
    }

    @Test
    @DisplayName("PUT /api/profile - bad request when contact code unknown")
    void update_badRequest_onUnknownContact() throws Exception {
        long id = authUser.id();
        ProfileTo withUnknownContact = ProfileTestData.getWithUnknownContactTo();

        // This will fail in ProfileUtil.checkContactsExist before calling repository
        mockMvc.perform(put(REST_URL)
                        .with(authentication(new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(authUser, authUser.getPassword(), authUser.getAuthorities())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(withUnknownContact)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().contentTypeCompatibleWith("application/problem+json"));
    }
}
