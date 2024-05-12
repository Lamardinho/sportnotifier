package com.lamardinho.sportnotifier.messages.telegram.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class TelegramSubscriberCreateDto {

    @NotBlank
    private Long chatId;

    private String userName;

    private String firstName;

    private String lastName;
}
