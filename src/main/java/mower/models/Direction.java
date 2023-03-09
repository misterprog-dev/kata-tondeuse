package mower.models;

import java.util.Arrays;
import java.util.Optional;

public enum Direction {
    NORTH("N"),
    EAST("E"),
    WEST("W"),
    SOUTH("S");
    private final String code;

    Direction(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public Direction getRight() {
        return switch(fromCode(code)) {
            case NORTH -> EAST;
            case SOUTH -> WEST;
            case WEST -> NORTH;
            case EAST -> SOUTH;
        };
    }

    public Direction getLeft() {
        return switch(fromCode(code)) {
            case NORTH -> WEST;
            case SOUTH -> EAST;
            case WEST -> SOUTH;
            case EAST -> NORTH;
        };
    }

    public static Direction fromCode(String code) {
        Optional<Direction> direction = Arrays.stream(values())
                .filter(value -> value.getCode().equalsIgnoreCase(code))
                .findFirst();
        return direction.orElse(null);
    }
}
