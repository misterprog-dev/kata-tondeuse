package mower.helpers;

import mower.exception.InvalidGardenSizeException;
import mower.models.Garden;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.stream;
import static org.apache.commons.lang3.StringUtils.isNumeric;

public class GardenHelper {
    public static Garden getLimitGarden(String[] line) throws InvalidGardenSizeException {
        if (isNotGardenSizeValid(line)) {
            throw new InvalidGardenSizeException("Garden size is invalid");
        }
        return new Garden(parseInt(line[0]), parseInt(line[1]));
    }

    static boolean isNotGardenSizeValid(String[] line) {
        return stream(line).count() < 2 || !isNumeric(line[0]) || !isNumeric(line[1]);
    }
}
