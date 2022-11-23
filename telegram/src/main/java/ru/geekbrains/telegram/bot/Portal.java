package ru.geekbrains.telegram.bot;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Portal {


    public static String getContent() throws IOException {
        JSONArray array = new JSONArray();
        Model memMessage = new Model();
        String tttt = "";

        URL url = new URL("http://localhost:8080/core-service/api/v1/mem/");
        Scanner scx = new Scanner((InputStream) url.getContent());
        while (scx.hasNext()) {
            tttt += scx.nextLine();
        }
        System.out.println(tttt);
//        array = (JSONArray) url.getContent();
        array.put(tttt);
        System.out.println(array);


        Model qwerty = new CustomJSONParser().parse(tttt);


        return "Наши супер мемы \n"+qwerty.getContent()+"\n"+"Из категории "+qwerty.getCategory()+".";
    }


}
