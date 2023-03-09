package mower.services;

import mower.exception.FileFormatInvalidException;
import mower.exception.InvalidGardenSizeException;
import mower.exception.MowerInitialPositionException;
import mower.models.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static mower.helpers.FileReaderHelper.readFile;
import static mower.helpers.GardenHelper.getLimitGarden;
import static org.apache.commons.lang3.StringUtils.isNumeric;

public class MowerService {
    private final List<Mower> mowers = new ArrayList<>();

    public MowerService(String filePath) throws IOException, FileFormatInvalidException, InvalidGardenSizeException, MowerInitialPositionException {
        List<String> fileLines = readFile(filePath);

        Iterator<String> fileLinesIterator = fileLines.iterator();
        String[] firstLineOfFile = fileLinesIterator.next().split(" ");
        LimitGarden limitGarden = getLimitGarden(firstLineOfFile);

        constructMowers(fileLinesIterator, limitGarden);
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

    public List<Position> launchMowers() {
        List<Position> positions = new ArrayList<>(mowers.size());
        mowers.forEach(mower -> positions.add(mower.run()));
        return positions;
    }

    public String displayFinalMowerPosition(List<Position> positions) {
        StringBuilder result = new StringBuilder();
        positions.forEach(position -> result.append(position.getFinalPosition()).append(" "));
        return result.toString().trim();
    }
}
