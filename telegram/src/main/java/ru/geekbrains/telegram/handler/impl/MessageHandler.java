package ru.geekbrains.telegram.handler.impl;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.geekbrains.telegram.dto.CustomJSONParser;
import ru.geekbrains.telegram.dto.Model;
import ru.geekbrains.telegram.handler.HandlerMessage;

import java.util.HashMap;
import java.util.Map;


public class MessageHandler implements HandlerMessage {


    private final CustomJSONParser customJSONParser;
    private Model model;
    private static final Map<Long, Integer> userCounter = new HashMap<>();

    public MessageHandler() {
        this.customJSONParser = new CustomJSONParser();
        this.model = new Model();
    }

    @Override
    public String sendMessageToUserNewMem(JSONArray array, Long chatId) {
        if (!userCounter.containsKey(chatId)) {
            userCounter.put(chatId, array.length() - 1);
        } else {
            userCounter.computeIfPresent(chatId, (k, v) -> v == array.length() ? array.length() - 1 : v);
        }
        if (userCounter.get(chatId) >= 0) {
            model = customJSONParser.parseJSON(getMessageFromURI(array, userCounter.get(chatId)));
            userCounter.computeIfPresent(chatId, (k, v) -> (v >= 0 ? --v : v));
            return " *** Шутка с мем портала из категории " + model.getCategory() + " *** \n" + model.getContent() + ".";
        } else {
            return "Шутки кончились. Листай в другую сторону";

        }
    }

    @Override
    public String sendMessageToUserOldMem(JSONArray array, Long chatId) {
        if (!userCounter.containsKey(chatId)) {
            userCounter.put(chatId, 0);
        } else {
            userCounter.computeIfPresent(chatId, (k, v) -> (v == -1 ? 0 : v));
        }
        if (userCounter.get(chatId) <= array.length() - 1) {
            model = customJSONParser.parseJSON(getMessageFromURI(array, userCounter.get(chatId)));
            userCounter.computeIfPresent(chatId, (k, v) -> (v <= array.length() - 1 ? ++v : v));
            return " *** Шутка с мем портала из категории " + model.getCategory() + " *** \n" + model.getContent() + ".";
        } else {
            return "Шутки кончились. Листай в другую сторону";
        }

    }


    private JSONObject getMessageFromURI(JSONArray array, int index) {
        return array.getJSONObject(index);

    }

}
