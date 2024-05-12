package com.lamardinho.sportnotifier.dtomappers;

import com.lamardinho.sportnotifier.entity.messages.subscriptions.TelegramSubscriber;
import com.lamardinho.sportnotifier.messages.telegram.dto.TelegramSubscriberCreateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface TelegramSubscriberMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateCreation", ignore = true)
    @Mapping(target = "dateLastSubscription", ignore = true)
    @Mapping(target = "dateLastUnSubscription", ignore = true)
    @Mapping(target = "active", ignore = true)
    TelegramSubscriber toEntity(TelegramSubscriberCreateDto dto);
}
