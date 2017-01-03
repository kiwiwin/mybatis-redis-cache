package com.github.kiwiwin.cache.domain;

import java.io.Serializable;

public class Product implements Serializable {
    private long id;
    private String description;
    private Color color;

    Product() {
    }

    public Product(String description, Color color) {
        this.description = description;
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Color getColor() {
        return color;
    }
}
