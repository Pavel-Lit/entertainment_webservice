package ru.geekbrains.telegram.bot;


import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.geekbrains.telegram.bot.enums.Menu;
import ru.geekbrains.telegram.bot.init.impl.KeyboardInitialImpl;
import ru.geekbrains.telegram.repository.Portal;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static ru.geekbrains.telegram.bot.enums.Commands.HELP;
import static ru.geekbrains.telegram.bot.enums.Commands.TRANSFER;
import static ru.geekbrains.telegram.bot.enums.Menu.MAIN;
import static ru.geekbrains.telegram.bot.enums.Menu.MEMS;

@Component
public class Bot extends TelegramLongPollingBot {

    @Value("${telegram.botName}")
    private final String botName;
    @Value("${telegram.botToken}")
    private final String token;


    public Bot() {
        this.botName = "firstTestTg_bot";
        this.token = "5555522218:AAGg-czdtqCD8lBtMOIs8mSlMOy7QRJuqNw";

    }


    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        Long chatId = message.getChatId();
        String row = message.getText().toUpperCase(Locale.ROOT);
        if (message.hasText()) {
            switch (row) {
                case "HELP":
                    sendMsg(
                            MAIN,
                            chatId,
                            "Для получения полной версии контента заплатите подписку $100," +
                                    " иначе только один мем \n перевод на карту по номеру телефона "
                    );
                    break;
                case "TRANSFER":
                    sendMsg(
                            MAIN,
                            chatId,
                            "Перевод денег на номер 8-800-355-35-35"
                    );
                    break;
                case "MEM":
                    sendMsg(
                            MEMS,
                            chatId,
                            "Мемы"
                    );
                    break;
                case "MENU":
                    sendMsg(
                            MAIN,
                            chatId, "Выход в меню");
                    break;
                case "NEXT":
                    sendMsg(
                            MEMS,
                            chatId, Portal.newMem(chatId)
                    );
                    break;
                case "BACK":
                    sendMsg(
                            MEMS,
                            chatId, Portal.oldMem(chatId)
                    );
                    break;
            }
        }
    }

    private void sendMsg(Menu menu, Long chatId, String text) {
        KeyboardInitialImpl replyKeyboard = new KeyboardInitialImpl();
        replyKeyboard.button(menu);
        try {
            execute(SendMessage.builder()
                    .chatId(chatId)
                    .text(text)
                    .replyMarkup(replyKeyboard.getRkm())
                    .build());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}
