package com.lamardinho.sportnotifier.messages.telegram;

import com.lamardinho.sportnotifier.dtomappers.TelegramSubscriberMapper;
import com.lamardinho.sportnotifier.messages.telegram.dto.TelegramSubscriberCreateDto;
import com.lamardinho.sportnotifier.repository.TelegramSubscriberRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Log4j2
public class TelegramSubscriberService {

    @NonNull
    private final TelegramSubscriberRepository repository;
    @NonNull
    private final TelegramSubscriberMapper mapper;

    @Transactional
    public void saveNewOrUpdateToActive(@NonNull TelegramSubscriberCreateDto dto) {
        val subscriber = repository.findByChatId(dto.getChatId());

        // create:
        if (subscriber.isEmpty()) {
            val entity = mapper.toEntity(dto);
            entity.setActive(true);
            repository.save(entity);
        }
        // update:
        else {
            val entity = subscriber.get();
            entity.setActive(true);
            entity.setDateLastSubscription(LocalDateTime.now());

            repository.save(subscriber.get());
        }
    }
}
