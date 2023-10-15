package com.lamardinho.sportnotifier.messages.service.impl;

import com.lamardinho.sportnotifier.config.LocalProfile;
import com.lamardinho.sportnotifier.messages.dto.TelegramSendMessageDTO;
import com.lamardinho.sportnotifier.messages.service.TelegramMessageService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
@Validated
@Log4j2
@LocalProfile
public class LocalTelegramMessageServiceImpl implements TelegramMessageService {

    @Override
    public void sendMessage(@NonNull TelegramSendMessageDTO dto) {
        log.info(dto.getText());
    }
}
