package ru.geekbrains.telegram.dto;

import org.json.JSONObject;
import ru.geekbrains.telegram.dto.Model;

public class CustomJSONParser {

    public Model parseJSON(JSONObject json){

        return Model.builder()
                .content(json.getString("content"))
                .category(json.getString("title"))
                .build();
    }


}
