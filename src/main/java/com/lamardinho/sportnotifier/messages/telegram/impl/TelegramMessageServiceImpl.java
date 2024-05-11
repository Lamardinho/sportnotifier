package com.lamardinho.sportnotifier.messages.telegram.impl;

import com.lamardinho.sportnotifier.config.util.AppProfile;
import com.lamardinho.sportnotifier.messages.telegram.TelegramMessageService;
import com.lamardinho.sportnotifier.messages.telegram.dto.TelegramSendMessageDTO;
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
@Profile({AppProfile.PROD})
public class TelegramMessageServiceImpl implements TelegramMessageService {

    @NonNull
    private final RestTemplate restTemplate;

    @Value("${app.telegram.send-msg-post-url-template}")
    private String sendMsgPostUrlTemplate;

    @Override
    public void sendMessage(@NonNull TelegramSendMessageDTO dto, @NonNull String botToken) {
        val url = String.format(sendMsgPostUrlTemplate, botToken, dto.getAddress(), dto.getText());
        restTemplate.postForEntity(url, null, String.class);
    }
}
