package com.example.calculatordemo_huynhphutrong_24162133;

import java.io.Serializable;

public class Fruit implements Serializable {
    public final String name;
    public final String emoji;
    public final String price;
    public final String origin;
    public final String vitamins;
    public final String description;
    public final int color; // màu nền của icon

    public Fruit(String name, String emoji, String price, String origin,
                 String vitamins, String description, int color) {
        this.name = name;
        this.emoji = emoji;
        this.price = price;
        this.origin = origin;
        this.vitamins = vitamins;
        this.description = description;
        this.color = color;
    }
}
