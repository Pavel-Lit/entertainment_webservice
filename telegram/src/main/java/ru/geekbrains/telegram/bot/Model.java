package ru.geekbrains.telegram.bot;

import lombok.Builder;
import org.json.JSONPropertyIgnore;

@Builder
public class Model {

    private String content;

    private String category;

    public Model(String content, String category) {
        this.content = content;
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Model() {
    }
}
