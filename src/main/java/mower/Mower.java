package mower;

public class Mower {
    private LimitGarden limitGarden;
    private Position position;

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
}
