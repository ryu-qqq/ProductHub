package com.ryuqq.setof.domain.core.product;

import java.util.Objects;

public class Color {
    private final long colorId;
    private final String colorName;

    public Color(long colorId, String colorName) {
        this.colorId = colorId;
        this.colorName = colorName;
    }

    public long getColorId() {
        return colorId;
    }

    public String getColorName() {
        return colorName;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Color color = (Color) object;
        return colorId == color.colorId && Objects.equals(colorName, color.colorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(colorId, colorName);
    }

    @Override
    public String toString() {
        return "Color{" +
                "colorId=" + colorId +
                ", colorName='" + colorName + '\'' +
                '}';
    }
}
