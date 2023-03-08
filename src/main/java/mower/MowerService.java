package mower;

import mower.exception.FileFormatInvalidException;
import mower.exception.InvalidGardenSizeException;
import mower.exception.MowerInitialPositionException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.stream;
import static org.apache.commons.lang3.StringUtils.isNumeric;

public class MowerService {
    private final List<Mower> mowers = new ArrayList<>();

    public MowerService(List<String> fileLines) throws FileNotFoundException, FileFormatInvalidException, InvalidGardenSizeException, MowerInitialPositionException {
        ValidateFileContent(fileLines);

        Iterator<String> fileLinesIterator = fileLines.iterator();
        String[] firstLineOfFile = fileLinesIterator.next().split(" ");
        LimitGarden limitGarden = getLimitGarden(firstLineOfFile);

        while(fileLinesIterator.hasNext()) {
            String mowerInitialPosition = fileLinesIterator.next();
            String[] splitOfMowerPosition = mowerInitialPosition.split(" ");

            if (stream(splitOfMowerPosition).count() < 3 || !isNumeric(splitOfMowerPosition[0]) || !isNumeric(splitOfMowerPosition[1])) {
                throw new MowerInitialPositionException("Invalid position for Mower");
            }

            mowers.add(new Mower(limitGarden, new Position(parseInt(splitOfMowerPosition[0]),
                            parseInt(splitOfMowerPosition[1]),
                            splitOfMowerPosition[2]))
            );
            fileLinesIterator.next();
        }
    }

    private static void ValidateFileContent(List<String> fileLines) throws FileNotFoundException, FileFormatInvalidException {
        if (fileLines == null) {
            throw new FileNotFoundException("File not exists");
        }
        if (fileLines.size() < 3 || fileLines.size() % 2 == 0) { // % 2 : each mower have 2 lines that concern it + first line to garden size.
            throw new FileFormatInvalidException("File format invalid");
        }
    }

    private LimitGarden getLimitGarden(String[] line) throws InvalidGardenSizeException {
        if (isNotGardenSizeValid(line)) {
            throw new InvalidGardenSizeException("Garden size is invalid");
        }
        return new LimitGarden(parseInt(line[0]), parseInt(line[1]));
    }

    private boolean isNotGardenSizeValid(String[] line) {
        return stream(line).count() < 2 || !isNumeric(line[0]) || !isNumeric(line[1]);
    }

    public List<Mower> getMowers() {
        return mowers;
    }
}
