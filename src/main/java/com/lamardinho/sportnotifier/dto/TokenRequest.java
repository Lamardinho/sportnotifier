package com.lamardinho.sportnotifier.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TokenRequest {

    @NotBlank
    private String token;
}
