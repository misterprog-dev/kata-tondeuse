package mower;

import java.util.Collection;
import java.util.List;

public class Mower {
    private LimitGarden limitGarden;
    private Position position;
    private List<String> commands;

    public Mower(LimitGarden limitGarden, Position position) {
        this.limitGarden = limitGarden;
        this.position = position;
    }

    public LimitGarden getLimitGarden() {
        return limitGarden;
    }

    public Position getPosition() {
        return position;
    }

    public List<String> getCommands() {
        return commands;
    }
}
