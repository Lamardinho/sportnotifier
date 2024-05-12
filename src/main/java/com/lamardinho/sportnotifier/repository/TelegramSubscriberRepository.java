package com.lamardinho.sportnotifier.repository;

import com.lamardinho.sportnotifier.entity.messages.subscriptions.TelegramSubscriber;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TelegramSubscriberRepository extends CrudRepository<TelegramSubscriber, Long> {

    Optional<TelegramSubscriber> findByChatId(Long chatId);
}
