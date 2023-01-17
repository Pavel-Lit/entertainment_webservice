package ru.geekbrains.telegram.handler;

import org.json.JSONArray;

public interface HandlerMessage {

    String sendMessageToUserNewMem(JSONArray array, Long chatId);


    String sendMessageToUserOldMem(JSONArray array, Long chatId);
}
