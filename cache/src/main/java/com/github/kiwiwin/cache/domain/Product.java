package com.github.kiwiwin.cache.domain;

import java.io.Serializable;

public class Product implements Serializable {
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
