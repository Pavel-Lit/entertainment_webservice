package ru.geekbrains.telegram.bot;


import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {

    public String getBotUsername() {
        return "firstTestTg_bot";
    }

    @Override
    public String getBotToken() {
        return "5555522218:AAGg-czdtqCD8lBtMOIs8mSlMOy7QRJuqNw";
    }

    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if(message != null && message.hasText()){
            // реализовать патерн comand вместо swith
            switch (message.getText()){
                case "/help":
                    sendMsg(message, "Для получения полной версии контента заплатите подписку $100, иначе только один мем \n перевод на карту по номеру телефона ");
                    break;
                case "перевод":
                    sendMsg(message, "8-800-355-35-35");
                    break;
                case "mem":
                    sendMsgNewKeyBoard(message, "text");
                    break;
                case "Menu":
                    sendMsg(message, "Выход в меню");
                    break;
                case "--->":
                    sendMsgNewKeyBoard(message, Portal.newMem(message.getChatId()));
                    break;
                case "<---":
                    sendMsgNewKeyBoard(message, Portal.oldMem(message.getChatId()));
                    break;

            }

        }
    }

    private String sendMsgNewKeyBoard(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId());

//        sendMessage.setReplyToMessageId(message.getMessageId()); //Добавляет имя пользователя и пересылаемое сообщение
        sendMessage.setText(text);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow firstRow = new KeyboardRow();
        KeyboardRow secondRow = new KeyboardRow();
        KeyboardRow thirdRow = new KeyboardRow();
        firstRow.add(new KeyboardButton("--->"));
        secondRow.add(new KeyboardButton("<---"));
        thirdRow.add(new KeyboardButton("Menu"));
        keyboardRows.add(firstRow);
        keyboardRows.add(secondRow);
        keyboardRows.add(thirdRow);
        replyKeyboardMarkup.setKeyboard(keyboardRows);

        try {
            execute(sendMessage);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return sendMessage.getChatId();
    }

    private String sendMsg(Message message, String text) {
        org.telegram.telegrambots.meta.api.methods.send.SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId());

//        sendMessage.setReplyToMessageId(message.getMessageId()); //Добавляет имя пользователя и пересылаемое сообщение
        sendMessage.setText(text);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow firstRow = new KeyboardRow();
        KeyboardRow secondRow = new KeyboardRow();
        KeyboardRow thirdRow = new KeyboardRow();
        firstRow.add(new KeyboardButton("/help"));
        secondRow.add(new KeyboardButton("mem"));
        thirdRow.add(new KeyboardButton("перевод"));
        keyboardRows.add(firstRow);
        keyboardRows.add(secondRow);
        keyboardRows.add(thirdRow);
        replyKeyboardMarkup.setKeyboard(keyboardRows);

        try {
            execute(sendMessage);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        return sendMessage.getChatId();
    }
}
