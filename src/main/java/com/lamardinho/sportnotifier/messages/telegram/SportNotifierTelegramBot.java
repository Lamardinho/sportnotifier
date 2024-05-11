package com.lamardinho.sportnotifier.messages.telegram;

import com.lamardinho.sportnotifier.common.AppException;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import static java.lang.String.format;

@Log4j2
public class SportNotifierTelegramBot extends TelegramLongPollingBot {

    /**
     * @param botToken - bot token for registration.
     */
    public SportNotifierTelegramBot(@NonNull String botToken) {
        super(botToken);
    }

    @Override
    public void onUpdateReceived(@NonNull Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            val message = update.getMessage();
            val text = message.getText();
            val chat = message.getChat();
            val chatId = message.getChatId();

            if (text.equalsIgnoreCase("/start")) {
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

                sendMessage(chatId, msg);

            } else if (text.equalsIgnoreCase(SportNotifierTelegramBotCommands.UEFA_CH_L_MATCHES_NOTIFICATIONS_SUBSCRIBE.getCommandValue())) {
                subscribeToMailingList(chatId);
                sendMessage(chatId, SUBSCRIPTION_COMPLETED_SUCCESSFULLY);

            } else if (text.equalsIgnoreCase(SportNotifierTelegramBotCommands.UEFA_CH_L_MATCHES_NOTIFICATIONS_UNSUBSCRIBE.getCommandValue())) {
                unsubscribeToMailingList(chatId);
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
        val sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (Exception e) {
            throw new AppException(e.getMessage(), e);
        }
    }

    @Override
    public String getBotUsername() {
        return "sportnotifier_bot";
    }

    private void subscribeToMailingList(Long chatId) {
        log.info(chatId);
    }

    private void unsubscribeToMailingList(Long chatId) {
        log.info(chatId);
    }

    private static final String SUBSCRIPTION_COMPLETED_SUCCESSFULLY = "subscription completed successfully";
    private static final String SUBSCRIPTION_CANCELED_SUCCESSFULLY = "subscription canceled successfully";
    private static final String COMMAND_COULD_NOT_BE_IDENTIFIED = "The command could not be identified.";
    private static final String LIST_OF_AVAILABLE_COMMANDS = "List of available commands:\n%s";
}
