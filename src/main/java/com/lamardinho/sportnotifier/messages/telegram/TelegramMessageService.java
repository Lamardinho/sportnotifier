package com.lamardinho.sportnotifier.messages.telegram;

import com.lamardinho.sportnotifier.messages.telegram.dto.TelegramSendMessageDTO;
import lombok.NonNull;

public interface TelegramMessageService {
    void sendMessage(
            @NonNull TelegramSendMessageDTO dto,
            @NonNull String botToken
    );
}
