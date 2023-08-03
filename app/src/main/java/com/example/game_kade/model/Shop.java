package com.example.game_kade.model;

public class Shop {
    String id;
    String name;
    String address;
    String time;
    String url;

    public Shop(String id,String name, String address, String time, String url) {
        this.name = name;
        this.address = address;
        this.time = time;
        this.url=url;
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getTime() {
        return time;
    }

    public String getUrl(){
        return url;
    }

    public String getId(){
        return id;
    }
}
