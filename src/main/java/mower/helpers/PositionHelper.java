package mower.helpers;

import mower.exception.FileFormatInvalidException;
import mower.exception.MowerInitialPositionException;
import mower.models.Direction;
import mower.models.Position;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.stream;
import static org.apache.commons.lang3.StringUtils.isNumeric;

public class PositionHelper {

    public static Position getMowerPosition(String position) throws MowerInitialPositionException, FileFormatInvalidException {
        String[] splitOfMowerPosition = position.split(" ");

        if (isNotInitialPositionValidForMower(splitOfMowerPosition)) {
            throw new MowerInitialPositionException("Invalid position for Mower");
        }

        if (isNotValidDirectionForMowerInitialPosition(splitOfMowerPosition)) {
            throw new FileFormatInvalidException("Invalid direction for Initial position");
        }

        return new Position(parseInt(splitOfMowerPosition[0]), parseInt(splitOfMowerPosition[1]), Direction.fromCode(splitOfMowerPosition[2]));
    }

    static boolean isNotInitialPositionValidForMower(String[] line) {
        return stream(line).count() < 3 || !isNumeric(line[0]) || !isNumeric(line[1]);
    }

    static boolean isNotValidDirectionForMowerInitialPosition(String[] line) {
        return stream(line).count() >= 3 && Direction.fromCode(line[2]) == null;
    }
}
