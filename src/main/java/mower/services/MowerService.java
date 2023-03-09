package mower.services;

import mower.exception.FileFormatInvalidException;
import mower.exception.InvalidGardenSizeException;
import mower.exception.MowerInitialPositionException;
import mower.models.LimitGarden;
import mower.models.Mower;
import mower.models.Position;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static mower.helpers.FileReaderHelper.readFile;
import static mower.helpers.GardenHelper.getLimitGarden;
import static mower.helpers.MowerHelper.constructMowers;

public class MowerService {
    private final List<Mower> mowers = new ArrayList<>();

    public MowerService(String filePath) throws IOException, FileFormatInvalidException, InvalidGardenSizeException, MowerInitialPositionException {
        List<String> fileLines = readFile(filePath);

        Iterator<String> fileLinesIterator = fileLines.iterator();
        String[] firstLineOfFile = fileLinesIterator.next().split(" ");
        LimitGarden limitGarden = getLimitGarden(firstLineOfFile);

        mowers.addAll(constructMowers(fileLinesIterator, limitGarden));
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
