package mower.models;

public class Position {
    private int x;
    private int y;
    private Direction direction;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(int x, int y, Direction direction) {
        this(x, y);
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void turnRight() {
        direction = direction.getRight();
    }

    public void turnLeft() {
        direction = direction.getLeft();
    }

    public void goAhead(LimitGarden limitGarden) {
        switch(direction) {
            case NORTH:
                if (limitGarden.getY() > y) y += 1;
                break;
            case EAST:
                if (limitGarden.getX() > x) x += 1;
            case WEST:
                if (limitGarden.getX() > 0) x -= 1;
            case SOUTH:
                if (limitGarden.getY() > 0) y -= 1;
        };
    }
}
