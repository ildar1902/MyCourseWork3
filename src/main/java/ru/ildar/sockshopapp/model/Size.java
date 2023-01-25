package ru.ildar.sockshopapp.model;

import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.lang.Nullable;

public enum Size {
    XS(35),
    S_36(36),
    S_37(37),
    M_38(38),
    M_39(39),
    M_40(40),
    L_41(41),
    L_42(42),
    L_43(43),
    XL_44(44),
    XL_45(45),
    XL_46(46),
    XXL(47);
    private final int size;

    Size(int size) {
        this.size = size;
    }

    @JsonValue
    public int getSize() {
        return size;
    }

    @Nullable
    public static Size parse(int size) {
        for (Size s : values()) {
            if (Float.compare(s.size, size) == 0) {
                return s;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "" + getSize();
    }
}
