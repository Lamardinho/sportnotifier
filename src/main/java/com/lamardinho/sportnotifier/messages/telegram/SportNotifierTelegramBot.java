package com.lamardinho.sportnotifier.messages.telegram;

import com.lamardinho.sportnotifier.common.AppException;
import com.lamardinho.sportnotifier.messages.telegram.dto.TelegramSendMessageDTO;
import com.lamardinho.sportnotifier.messages.telegram.dto.TelegramSubscriberCreateDto;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import static java.lang.String.format;

@Log4j2
public class SportNotifierTelegramBot extends TelegramLongPollingBot {

    @NonNull
    private final TelegramSubscriberService telegramSubscriberService;
    @NonNull
    private final TelegramMessageService telegramMessageService;

    private final String thisBotToken;

    /**
     * @param botToken - bot token for registration.
     */
    public SportNotifierTelegramBot(
            @NonNull String botToken,
            @NonNull TelegramSubscriberService telegramSubscriberService,
            @NonNull TelegramMessageService telegramMessageService
    ) {
        super(botToken);
        this.telegramSubscriberService = telegramSubscriberService;
        this.telegramMessageService = telegramMessageService;
        this.thisBotToken = botToken;
    }

    @Override
    public void onUpdateReceived(@NonNull Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            val message = update.getMessage();
            val text = message.getText();
            val chat = message.getChat();
            val chatId = message.getChatId();

            if (text.equalsIgnoreCase(START)) {
                val msg = format(
                        """
                                Hello @%s. Welcome to us!

                                %s""",
                        chat.getUserName(),
                        format(
                                LIST_OF_AVAILABLE_COMMANDS,
                                SportNotifierTelegramBotCommands.getCommandValuesString()
                        )
                );

                val dto =
                        new TelegramSubscriberCreateDto()
                                .setChatId(chatId)
                                .setUserName(chat.getUserName())
                                .setFirstName(chat.getFirstName())
                                .setLastName(chat.getLastName());
                telegramSubscriberService.saveNewOrUpdateToActive(dto);
                log.info(
                        "subscriber with {} was registered in the '{}'.",
                        (chat.getUserName() == null)
                                ? ("chatId: '" + chatId + "'") : ("user_name: '" + chat.getUserName() + "'"),
                        getBotUsername()
                );

                sendMessage(chatId, msg);

            } else if (text.equalsIgnoreCase(SportNotifierTelegramBotCommands.UEFA_CH_L_MATCHES_NOTIFICATIONS_SUBSCRIBE.getCommandValue())) {
                subscribeToNotificationList(chatId);
                sendMessage(chatId, SUBSCRIPTION_COMPLETED_SUCCESSFULLY);

            } else if (text.equalsIgnoreCase(SportNotifierTelegramBotCommands.UEFA_CH_L_MATCHES_NOTIFICATIONS_UNSUBSCRIBE.getCommandValue())) {
                unsubscribeToNotificationList(chatId);
                sendMessage(chatId, SUBSCRIPTION_CANCELED_SUCCESSFULLY);

            } else {
                sendMessage(
                        chatId,
                        COMMAND_COULD_NOT_BE_IDENTIFIED + "\n\n" + format(
                                LIST_OF_AVAILABLE_COMMANDS,
                                SportNotifierTelegramBotCommands.getCommandValuesString()
                        )
                );
            }
        }
    }

    private void sendMessage(long chatId, @NonNull String text) {
        log.info("Отправляю сообщение для chatId: {}", chatId);
        try {
            // old impl: val sendMessage = new SendMessage(); sendMessage.setChatId(chatId); sendMessage.setText(text); execute(sendMessage);  //NOSONAR
            log.info("Отправляю сообщение для chatId: {}", chatId);
            telegramMessageService.sendMessage(
                    new TelegramSendMessageDTO(String.valueOf(chatId), text),
                    thisBotToken
            );
        } catch (Exception e) {
            throw new AppException(e.getMessage(), e);
        }
    }

    @Override
    public String getBotUsername() {
        return "sportnotifier_bot";
    }

    private void subscribeToNotificationList(Long chatId) {
        log.info("subscribeToNotificationList: {}", chatId);
    }

    private void unsubscribeToNotificationList(Long chatId) {
        log.info("unsubscribeToNotificationList: {}", chatId);
    }

    private static final String START = "/start";
    private static final String SUBSCRIPTION_COMPLETED_SUCCESSFULLY = "subscription completed successfully";
    private static final String SUBSCRIPTION_CANCELED_SUCCESSFULLY = "subscription canceled successfully";
    private static final String COMMAND_COULD_NOT_BE_IDENTIFIED = "The command could not be identified.";
    private static final String LIST_OF_AVAILABLE_COMMANDS = "List of available commands:\n%s";
}
