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

    public void goAhead(Garden garden) {
        switch(direction) {
            case NORTH:
                if (garden.getY() > y) y += 1;
                break;
            case EAST:
                if (garden.getX() > x) x += 1;
                break;
            case WEST:
                if (garden.getX() > 0) x -= 1;
                break;
            case SOUTH:
                if (garden.getY() > 0) y -= 1;
                break;
        };
    }

    public String getFinalPosition() {
        return x + " " + y + " " + direction.getCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Position other = (Position) obj;

        return other.getY() == y && other.getX() == x && other.getDirection() == direction;
    }
}
