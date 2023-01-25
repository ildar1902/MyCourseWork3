package ru.ildar.sockshopapp.model;

import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.lang.Nullable;

public enum Color {
    WHITE("белый"),
    BLACK("чёрный"),
    GRAY("серый"),
    BLUE("синий"),
    GREEN("зелёный"),
    RED("красный"),
    YELLOW("жёлтый");

    private final String color;

    Color(String color) {
        this.color = color;
    }

    @JsonValue
    public String getColor() {
        return color;
    }

    @Nullable
    public static Color parse(String color) {
        for (Color c : values()) {
            if (c.getColor().equals(color)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return getColor();
    }
}
