package mower;

import mower.exception.FileFormatInvalidException;
import mower.exception.InvalidGardenSizeException;
import mower.exception.MowerInitialPositionException;
import mower.models.Position;
import mower.services.MowerService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Application {

    public static void main(final String[] args) throws InvalidGardenSizeException, MowerInitialPositionException, IOException, FileFormatInvalidException {
        if (args == null || args.length < 1) {
            throw new FileNotFoundException("File Not found");
        }

        MowerService mowerService = new MowerService(args[0]);
        List<Position> positions = mowerService.launchMowers();
        System.out.println(mowerService.getFinalMowersPositions(positions));
    }
}
