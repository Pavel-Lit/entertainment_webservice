package ru.geekbrains.telegram.bot;

import org.json.JSONArray;
import org.json.JSONObject;

public class CustomJSONParser {
    public Model parse(String json){
        Model model = new Model();
       String efbviebf = json.substring(1, json.length()-1);
        JSONObject object = new JSONObject(efbviebf);
        System.out.println(object);
        model.setContent(object.getString("content"));
        model.setCategory(object.getString("title"));


        return model;
    }
}
