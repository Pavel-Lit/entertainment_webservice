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
//        model.setContent(object.getString("{"));
//        JSONObject main = object.getJSONObject("main");
        model.setCategory(object.getString("title"));
//        model.setHumidity(main.getDouble("humidity"));
//        JSONArray jsonArray = object.getJSONArray("weather");
//        for (int i = 0; i < jsonArray.length(); i++) {
//            JSONObject temp = jsonArray.getJSONObject(i);
//            model.setMain(String.valueOf(temp.get("main")));
//            model.setIcon(String.valueOf(temp.get("icon")));

//        }

        return model;
    }
}
