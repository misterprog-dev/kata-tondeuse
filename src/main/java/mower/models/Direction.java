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

    public Direction getRight() {
        Direction direction = fromCode(code);

        if (code.equals(NORTH.getCode())) {
            direction = EAST;
        }

        if (code.equals(SOUTH.getCode())) {
            direction = WEST;
        }

        if (code.equals(WEST.getCode())) {
            direction = NORTH;
        }

        if (code.equals(EAST.getCode())) {
            direction = SOUTH;
        }

        return direction;
    }
}
