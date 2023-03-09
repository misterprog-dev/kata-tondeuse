package mower.helpers;

import mower.exception.FileFormatInvalidException;
import mower.exception.MowerInitialPositionException;
import mower.models.Command;
import mower.models.Garden;
import mower.models.Mower;
import mower.models.Position;

import java.util.*;

import static java.util.stream.Collectors.toList;
import static mower.helpers.PositionHelper.getMowerPosition;

public class MowerHelper {

    public static List<Mower> constructMowers(Iterator<String> fileLinesIterator, Garden garden) throws MowerInitialPositionException, FileFormatInvalidException {
        List<Mower> mowers = new ArrayList<>();

        while(fileLinesIterator.hasNext()) {
            Position position = getMowerPosition(fileLinesIterator.next());
            List<Command> mowerCommands = getCommands(fileLinesIterator.next()).stream()
                    .map(Command::fromCode)
                    .filter(Objects::nonNull)
                    .collect(toList());
            mowers.add(new Mower(garden, position, mowerCommands));
        }

        return mowers;
    }
    static List<String> getCommands(String mowerControlLine) {
        return Arrays.asList(mowerControlLine.split(""));
    }
}
