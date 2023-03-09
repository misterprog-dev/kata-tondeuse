package mower;


import mower.exception.FileFormatInvalidException;
import mower.exception.InvalidGardenSizeException;
import mower.exception.MowerInitialPositionException;
import mower.models.Command;
import mower.models.Mower;
import mower.models.Position;
import mower.services.MowerService;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static mower.Constant.baseDirForTestData;
import static mower.models.Direction.*;
import static org.junit.Assert.assertEquals;

public class MowerServiceTest {

    @Test
    public void should_constructMower_WithLimitGardenAndPositionAndCommand() throws InvalidGardenSizeException, IOException, FileFormatInvalidException, MowerInitialPositionException {
        // when
        MowerService mowerService = new MowerService(baseDirForTestData + "ConstructMower.txt");

        // then
        List<Mower> mowers = mowerService.getMowers();
        assertEquals(mowers.size(), 2);
        Mower firstMower = mowers.get(0);
        assertEquals(firstMower.getPosition().getX(), 1);
        assertEquals(firstMower.getPosition().getDirection(), fromCode("N"));
        assertEquals(firstMower.getCommands().size(), 8);
        List<String> commandsValue = firstMower.getCommands().stream()
                        .map(Command::getCode)
                        .collect(toList());
        assertEquals(commandsValue, asList("G","A", "G", "A", "G", "A", "G", "A"));
    }

    @Test
    public void should_return_mower_position_with_turnRight() throws InvalidGardenSizeException, MowerInitialPositionException, IOException, FileFormatInvalidException {
        // Given
        MowerService mowerService = new MowerService(baseDirForTestData + "TurnRight.txt");

        // When
        List<Position> results = mowerService.launchMowers();

        // Then
        assertEquals(results.size(), 1);
        assertEquals(results.get(0), new Position(1, 2, SOUTH));
    }

    @Test
    public void should_return_mower_position_with_turnLeft() throws InvalidGardenSizeException, MowerInitialPositionException, IOException, FileFormatInvalidException {
        // Given
        MowerService mowerService = new MowerService(baseDirForTestData + "TurnLeft.txt");

        // When
        List<Position> results = mowerService.launchMowers();

        // Then
        assertEquals(results.size(), 1);
        assertEquals(results.get(0), new Position(1, 1, WEST));
    }

    @Test
    public void should_return_mower_position_with_goAhead() throws InvalidGardenSizeException, MowerInitialPositionException, IOException, FileFormatInvalidException {
        // Given
        MowerService mowerService = new MowerService(baseDirForTestData + "GoAhead.txt");

        // When
        List<Position> results = mowerService.launchMowers();

        // Then
        assertEquals(results.size(), 1);
        assertEquals(results.get(0), new Position(1, 2, NORTH));
    }

    @Test
    public void should_return_mower_position_with_goAhead_and_passLimit() throws InvalidGardenSizeException, MowerInitialPositionException, IOException, FileFormatInvalidException {
        // Given
        MowerService mowerService = new MowerService(baseDirForTestData + "GoAheadAndPassLimit.txt");

        // When
        List<Position> results = mowerService.launchMowers();

        // Then
        assertEquals(results.size(), 1);
        assertEquals(results.get(0), new Position(2, 2, NORTH));
    }

    @Test
    public void should_display_mower_final_position() throws InvalidGardenSizeException, MowerInitialPositionException, IOException, FileFormatInvalidException {
        // Given
        MowerService mowerService = new MowerService(baseDirForTestData + "MowerFinalPosition.txt");
        List<Position> positions = mowerService.launchMowers();

        // When
        String result = mowerService.displayFinalMowerPosition(positions);

        // Then
        assertEquals(result, "1 3 N 5 1 E");
    }
}