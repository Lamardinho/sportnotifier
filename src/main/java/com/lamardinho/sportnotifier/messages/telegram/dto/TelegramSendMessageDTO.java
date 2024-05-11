package com.lamardinho.sportnotifier.messages.telegram.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Schema(description = "Для отправки сообщений")
@Data
@AllArgsConstructor
public class TelegramSendMessageDTO {

    @Schema(description = "адресат/чат id")
    @NonNull
    @NotBlank
    private String address;

    @Schema(description = "сообщение")
    @NonNull
    @NotBlank
    private String text;
}
