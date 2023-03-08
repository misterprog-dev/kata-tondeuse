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

    public Position getPosition() {
        return position;
    }

    public List<Command> getCommands() {
        return commands;
    }

    public Position run() {
        for (Command command : commands) {
            move(command);
        }
        return position;
    }

    private void move(Command command) {
        switch (command) {
            case RIGHT -> position.turnRight();
            case LEFT -> position.turnLeft();
            case GO_AHEAD -> position.goAhead(limitGarden);
        };
    }
}
