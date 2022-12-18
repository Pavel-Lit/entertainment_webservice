package ru.geekbrains.telegram.bot;

import lombok.SneakyThrows;
import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class Portal {


    public static String newMem(Long chatId) throws IOException {
        MessageHandler messageHandler = new MessageHandler();
        return messageHandler.sendMessageToUserNewMem(getArray(), chatId);

    }
    @SneakyThrows
    public static String oldMem(Long chatId){
        MessageHandler messageHandler = new MessageHandler();
        return messageHandler.sendMessageToUserOldMem(getArray(), chatId);
    }

    public static JSONArray getArray() throws IOException {
        String incomMsg = "";
        URL url = new URL("http://localhost:8080/core-service/api/v1/mem/");
        Scanner scx = new Scanner((InputStream) url.getContent());
        while (scx.hasNext()) {
            incomMsg += scx.nextLine();
        }

        return new JSONArray(incomMsg);
    }


}
