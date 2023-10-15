package com.lamardinho.sportnotifier.messages.service.impl;

import com.lamardinho.sportnotifier.messages.dto.TelegramSendMessageDTO;
import com.lamardinho.sportnotifier.messages.service.TelegramMessageService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Validated
@Profile("!local")
public class TelegramMessageServiceImpl implements TelegramMessageService {

    @NonNull
    private final RestTemplate restTemplate;

    @Value("${app.telegram.bot.token}")
    private String botToken;
    @Value("${app.telegram.bot.send-msg-post-url-template}")
    private String sendMsgPostUrlTemplate;

    @Override
    public void sendMessage(@NonNull TelegramSendMessageDTO dto) {
        val url = String.format(sendMsgPostUrlTemplate, botToken, dto.getAddress(), dto.getText());
        restTemplate.postForEntity(url, null, String.class);
    }
}
