package ru.geekbrains.telegram.bot;

import org.json.JSONObject;

public class CustomJSONParser {

    public  Model parseJSON(JSONObject json){

        return Model.builder()
                .content(json.getString("content"))
                .category(json.getString("title"))
                .build();
    }


}
