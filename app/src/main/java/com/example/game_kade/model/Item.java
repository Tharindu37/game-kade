package com.example.game_kade.model;

public class Item {
    private String id;
    private String categoryId;
    private String name;
    private String description;
    private String url;
    private double price;

    public Item(String id, String categoryId, String name, String description, String url, double price) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.url = url;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public double getPrice() {
        return price;
    }
}
