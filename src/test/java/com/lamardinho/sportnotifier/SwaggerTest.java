package com.lamardinho.sportnotifier;

import com.lamardinho.sportnotifier.config.ControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ControllerTest
class SwaggerTest {

    @Autowired
    private MockMvc mockMvc;

    private final static String BASE_URL = "http://localhost:8090/swagger-ui/index.html";

    @Test
    @WithMockUser(username = "user", roles = "USER")
    void when_authenticated_then_ok() throws Exception {
        mockMvc.perform(
                        get(BASE_URL)
                )
                .andExpect(status().isOk());
    }

    @Test
    void when_anonymous_then_forbidden() throws Exception {
        mockMvc.perform(
                        get(BASE_URL)
                )
                .andExpect(status().isUnauthorized());
    }
}
