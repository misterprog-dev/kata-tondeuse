package mower.services;

import mower.exception.FileFormatInvalidException;
import mower.exception.InvalidGardenSizeException;
import mower.exception.MowerInitialPositionException;
import mower.models.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.isNumeric;

public class MowerService {
    private final List<Mower> mowers = new ArrayList<>();

    public MowerService(List<String> fileLines) throws FileNotFoundException, FileFormatInvalidException, InvalidGardenSizeException, MowerInitialPositionException {
        ValidateFileContent(fileLines);

        Iterator<String> fileLinesIterator = fileLines.iterator();
        String[] firstLineOfFile = fileLinesIterator.next().split(" ");
        LimitGarden limitGarden = getLimitGarden(firstLineOfFile);

        constructMowers(fileLinesIterator, limitGarden);
    }

    private void ValidateFileContent(List<String> fileLines) throws FileNotFoundException, FileFormatInvalidException {
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

    private void constructMowers(Iterator<String> fileLinesIterator, LimitGarden limitGarden) throws MowerInitialPositionException {
        while(fileLinesIterator.hasNext()) {
            Position mowerPosition = getPosition(fileLinesIterator.next());
            List<Command> mowerCommands = getCommands(fileLinesIterator.next()).stream()
                    .map(Command::fromCode)
                    .collect(toList());
            mowers.add(new Mower(limitGarden, mowerPosition, mowerCommands));
        }
    }

    private boolean isNotGardenSizeValid(String[] line) {
        return stream(line).count() < 2 || !isNumeric(line[0]) || !isNumeric(line[1]);
    }

    private Position getPosition(String mowerPosition) throws MowerInitialPositionException {
        String[] splitOfMowerPosition = mowerPosition.split(" ");

        if (isNotInitialPositionValidForMower(splitOfMowerPosition)) {
            throw new MowerInitialPositionException("Invalid position for Mower");
        }

        return new Position(parseInt(splitOfMowerPosition[0]), parseInt(splitOfMowerPosition[1]), Direction.fromCode(splitOfMowerPosition[2]));
    }

    private boolean isNotInitialPositionValidForMower(String[] line) {
        return stream(line).count() < 3 || !isNumeric(line[0]) || !isNumeric(line[1]);
    }

    private List<String> getCommands(String mowerControlLine) {
        return Arrays.asList(mowerControlLine.split(""));
    }

    public List<Mower> getMowers() {
        return mowers;
    }
}
