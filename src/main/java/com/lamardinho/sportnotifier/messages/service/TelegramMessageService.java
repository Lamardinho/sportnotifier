package com.lamardinho.sportnotifier.messages.service;

import com.lamardinho.sportnotifier.messages.dto.TelegramSendMessageDTO;
import lombok.NonNull;

public interface TelegramMessageService {
    void sendMessage(@NonNull TelegramSendMessageDTO dto);
}
