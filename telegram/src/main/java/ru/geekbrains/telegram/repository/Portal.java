package ru.geekbrains.telegram.repository;

import org.json.JSONArray;
import ru.geekbrains.telegram.handler.HandlerMessage;
import ru.geekbrains.telegram.handler.impl.MessageHandler;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class Portal {


    public static String newMem(Long chatId) throws IOException {
        HandlerMessage messageHandler = new MessageHandler();
        return messageHandler.sendMessageToUserNewMem(getArray(), chatId);

    }

    public static String oldMem(Long chatId) throws IOException {
        HandlerMessage messageHandler = new MessageHandler();
        return messageHandler.sendMessageToUserOldMem(getArray(), chatId);
    }



    private static JSONArray getArray() throws IOException {
        StringBuilder incomeMsg = new StringBuilder();
        URL url = new URL("http://localhost:8080/core-service/api/v1/mem/");
        Scanner scx = new Scanner((InputStream) url.getContent());
        while (scx.hasNext()) {
            incomeMsg.append(scx.nextLine());
        }

        return new JSONArray(incomeMsg.toString());
    }


}
