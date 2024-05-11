package com.lamardinho.sportnotifier.messages.telegram.impl;

import com.lamardinho.sportnotifier.messages.telegram.dto.TelegramSendMessageDTO;
import com.lamardinho.sportnotifier.messages.telegram.TelegramMessageService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
@Validated
@Log4j2
@Profile({"default", "local", "test"})
public class FakeTelegramMessageServiceImpl implements TelegramMessageService {

    @Override
    public void sendMessage(@NonNull TelegramSendMessageDTO dto, @NonNull String botToken) {
        log.info(dto.getText(), botToken);
    }
}
