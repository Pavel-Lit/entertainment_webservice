package ru.geekbrains.telegram.bot;


import org.apache.http.impl.auth.UnsupportedDigestAlgorithmException;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "firstTestTg_bot";
    }

    @Override
    public String getBotToken() {
        return "5555522218:AAGg-czdtqCD8lBtMOIs8mSlMOy7QRJuqNw";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
//        Model model = new Model();
        if(message != null && message.hasText()){

            switch (message.getText()){
                case "/help":
                    sendMsg(message, "Для получения полной версии контента заплатите подписку $100, иначе только один мем \n перевод на карту по номеру телефона ");
                    break;
                case "перевод":
                    sendMsg(message, "8-800-355-35-35");
                    break;
//                    case "перевод"
//                case "mem":
//                    try {
//                        sendMsg(message, Portal.getContent());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                default:
                    try{
//                        sendMsg(message, UnsupportedDigestAlgorithmException.getWeather(message.getText()));
                            sendMsg(message, Portal.getContent());
//                        sendImage(message);
                    } catch (IOException e){
                        sendMsg(message, "Для помощи введите /help");
                    }
            }

        }
    }
    private void sendMsg(Message message, String text) {
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
        KeyboardRow secondRow2 = new KeyboardRow();
        firstRow.add(new KeyboardButton("/help"));
        secondRow.add(new KeyboardButton("mem"));
        secondRow2.add(new KeyboardButton("перевод"));
        keyboardRows.add(firstRow);
        keyboardRows.add(secondRow);
        keyboardRows.add(secondRow2);
        replyKeyboardMarkup.setKeyboard(keyboardRows);

        try {
            execute(sendMessage);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
