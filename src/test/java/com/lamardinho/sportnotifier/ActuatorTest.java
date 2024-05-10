package com.lamardinho.sportnotifier;

import com.lamardinho.sportnotifier.config.ControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ControllerTest
class ActuatorTest {

    @Autowired
    private MockMvc mockMvc;

    private final static String BASE_URL = "http://localhost:8090/actuator";

    @Test
    void when_anonymous_then_ok() throws Exception {
        mockMvc.perform(
                        get(BASE_URL)
                )
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    void when_authenticated_then_ok() throws Exception {
        mockMvc.perform(
                        get(BASE_URL)
                )
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", authorities = "APP_ADMIN")
    void env_when_APP_ADMIN_then_ok() throws Exception {
        mockMvc.perform(
                        get(BASE_URL + "/env")
                )
                .andExpect(status().isOk());
    }

    @Test
    void env_when_anonymous_then_isForbidden() throws Exception {
        mockMvc.perform(
                        get(BASE_URL + "/env")
                )
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    void env_when_NO_APP_ADMIN_then_isForbidden() throws Exception {
        mockMvc.perform(
                        get(BASE_URL + "/env")
                )
                .andExpect(status().isForbidden());
    }
}
