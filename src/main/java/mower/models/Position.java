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

    public void goAhead() {
        switch(direction) {
            case NORTH -> y += 1;
            case EAST -> x += 1;
            case WEST -> x -= 1;
            case SOUTH -> y -=1;
        };
    }
}
