package ru.geekbrains.telegram.bot;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MessageHandler {


    private CustomJSONParser customJSONParser;
    private Model model;
    private static Map<Long, Integer> userCounter = new HashMap<>();

    public MessageHandler() {
        this.customJSONParser = new CustomJSONParser();
        this.model = new Model();
    }

    public String sendMessageToUserNewMem(JSONArray array, Long chatId) {
        if (!userCounter.containsKey(chatId)) {
            userCounter.put(chatId, array.length() - 1);
        } else {
            userCounter.computeIfPresent(chatId, (k, v) -> v == array.length() ? array.length() - 1 : v);
        }
        if (userCounter.get(chatId) >= 0) {
            model = customJSONParser.parseJSON(getMessageFromURI(array, userCounter.get(chatId)));
            userCounter.computeIfPresent(chatId, (k, v) -> (v >= 0 ? --v : v));
            return "Свежии шутки с мем портала \n" + model.getContent() + "\nиз категории " + model.getCategory() + ".";
        } else {
            return "Шутки кончились. Листай назад сторону";

        }
    }

    public String sendMessageToUserOldMem(JSONArray array, Long chatId) {
        if (!userCounter.containsKey(chatId)) {
            userCounter.put(chatId, 0);
        } else {
            userCounter.computeIfPresent(chatId, (k, v) -> (v == -1 ? 0 : v));
        }
        if (userCounter.get(chatId) <= array.length() - 1) {
            model = customJSONParser.parseJSON(getMessageFromURI(array, userCounter.get(chatId)));
            userCounter.computeIfPresent(chatId, (k, v) -> (v <= array.length() - 1 ? ++v : v));
            return "Старые шутки с мем портала \n" + model.getContent() + "\nиз категории " + model.getCategory() + ".";
        } else {
            return "Шутки кончились. Листай вперед сторону";
        }

    }


    private JSONObject getMessageFromURI(JSONArray array, int index) {
        return array.getJSONObject(index);

    }

}
