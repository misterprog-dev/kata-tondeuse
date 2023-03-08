package mower.models;

import java.util.Arrays;
import java.util.Optional;

public enum Command {

    GO_AHEAD("A"),
    RIGHT("D"),
    LEFT("G");
    private String code;

    Command(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Command fromCode(String code) {
        Optional<Command> command = Arrays.stream(values())
                .filter(value -> value.getCode().equalsIgnoreCase(code))
                .findFirst();
        return command.orElse(null);
    }
}
