package mower.models;

import java.util.List;

public class Mower {
    private LimitGarden limitGarden;
    private Position position;
    private List<Command> commands;

    public Mower(LimitGarden limitGarden, Position position) {
        this.limitGarden = limitGarden;
        this.position = position;
    }

    public Mower(LimitGarden limitGarden, Position position, List<Command> commands) {
        this(limitGarden, position);
        this.commands = commands;
    }

    public LimitGarden getLimitGarden() {
        return limitGarden;
    }

    public Position getPosition() {
        return position;
    }

    public List<Command> getCommands() {
        return commands;
    }
}
