package com.lamardinho.sportnotifier.controller.api.pub;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lamardinho.sportnotifier.config.ControllerTest;
import com.lamardinho.sportnotifier.dto.RegistrationDTO;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * {@link RegistrationController}
 */
@ControllerTest
class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final static String BASE_URL = "http://localhost:8090/api/public/registration";

    @Test
    void registration_when_anonymous_then_isOk() throws Exception {
        val dto = getRegistrationDTO();

        mockMvc.perform(
                        post(BASE_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(dto))
                )
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(username = "user", roles = "USER")
    void registration_when_authenticated_then_isOk() throws Exception {
        val dto = getRegistrationDTO();

        mockMvc.perform(
                        post(BASE_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(dto))
                )
                .andExpect(status().isOk());
    }

    private static RegistrationDTO getRegistrationDTO() {
        return new RegistrationDTO()
                .setLogin("newUser")
                .setLogin("Qwerty123")
                ;
    }
}
