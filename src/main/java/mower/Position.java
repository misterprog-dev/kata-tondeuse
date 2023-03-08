package mower;

public class Position {
    private int x;
    private int y;
    private String direction;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(int x, int y, String direction) {
        this(x, y);
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getDirection() {
        return direction;
    }
}
