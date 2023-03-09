package mower.helpers;

import mower.models.Direction;
import mower.models.Position;

import static mower.models.Direction.WEST;

public class PositionHelper {

    public static Position getMowerPosition(String position) {
        return new Position(0, 0, WEST);
    }
}
