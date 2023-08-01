package com.example.game_kade.model;

public class Category {
    public String name;
    public String url;

    public String id;

    public Category(String name, String url, String id) {
        this.name = name;
        this.url = url;
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getId() {
        return id;
    }
}
