package com.github.kiwiwin.cache.core;

public class Product {
    private long id;
    private String description;

    Product() {
    }

    public Product(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
