package com.lamardinho.sportnotifier.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RegistrationDTO {

    @NotBlank
    private String login;

    @NotBlank
    private String password;
}
