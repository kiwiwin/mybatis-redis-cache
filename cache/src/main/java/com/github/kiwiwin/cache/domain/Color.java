package com.github.kiwiwin.cache.domain;

import com.google.inject.Injector;

import javax.inject.Inject;
import java.io.Serializable;

public class Color implements Serializable {
    @Inject
    protected transient Injector injector;

    private String color;

    Color() {
    }

    public Color(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
