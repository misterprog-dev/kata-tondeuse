package mower.models;

import java.util.Arrays;
import java.util.Optional;

public enum Direction {
    NORTH("N"),
    EAST("E"),
    WEST("W"),
    SOUTH("S");
    private String code;

    Direction(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Direction fromCode(String code) {
        Optional<Direction> direction = Arrays.stream(values())
                .filter(value -> value.getCode().equalsIgnoreCase(code))
                .findFirst();
        return direction.orElse(null);
    }
}
