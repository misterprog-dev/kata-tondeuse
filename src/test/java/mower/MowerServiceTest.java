package mower;


import mower.exception.FileFormatInvalidException;
import mower.exception.InvalidGardenSizeException;
import mower.exception.MowerInitialPositionException;
import mower.models.Command;
import mower.models.Direction;
import mower.models.Mower;
import mower.models.Position;
import mower.services.MowerService;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static mower.Constant.baseDirForTestData;
import static org.junit.Assert.*;

public class MowerServiceTest {

    @Test
    public void should_return_exception_for_invalid_garden() {
        // when
        Exception exception = assertThrows(InvalidGardenSizeException.class, () -> new MowerService(baseDirForTestData + "InvalidGarden.txt"));

        // then
        String expectedMessage = "Garden size is invalid";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void should_constructMower_WithLimitGardenAndPositionAndCommand() throws InvalidGardenSizeException, IOException, FileFormatInvalidException, MowerInitialPositionException {
        // when
        MowerService mowerService = new MowerService(baseDirForTestData + "ConstructMower.txt");

        // then
        List<Mower> mowers = mowerService.getMowers();
        assertEquals(mowers.size(), 2);
        Mower firstMower = mowers.get(0);
        assertEquals(firstMower.getPosition().getX(), 1);
        assertEquals(firstMower.getPosition().getDirection(), Direction.fromCode("N"));
        assertEquals(firstMower.getCommands().size(), 8);
        List<String> commandsValue = firstMower.getCommands().stream()
                        .map(Command::getCode)
                        .collect(toList());
        assertEquals(commandsValue, asList("G","A", "G", "A", "G", "A", "G", "A"));
    }

    @Test
    public void should_return_exception_for_invalid_mowerInitalPosition() {
        // when
        Exception exception = assertThrows(MowerInitialPositionException.class, () -> new MowerService(baseDirForTestData + "InvalidMowerInitialPosition.txt"));

        // then
        String expectedMessage = "Invalid position for Mower";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void should_return_position_for_all_mower() throws InvalidGardenSizeException, MowerInitialPositionException, IOException, FileFormatInvalidException {
        // Given
        MowerService mowerService = new MowerService(baseDirForTestData + "PositionForAllMower.txt");

        // When
        List<Position> results = mowerService.launchMowers();

        // Then
        assertEquals(results.size(), 2);
    }

    @Test
    public void should_return_mower_position_with_turnRight() throws InvalidGardenSizeException, MowerInitialPositionException, IOException, FileFormatInvalidException {
        // Given
        MowerService mowerService = new MowerService(baseDirForTestData + "TurnRight.txt");

        // When
        List<Position> results = mowerService.launchMowers();

        // Then
        assertEquals(results.size(), 1);
        assertEquals(results.get(0).getX(), 1);
        assertEquals(results.get(0).getY(), 2);
        assertEquals(results.get(0).getDirection(), Direction.fromCode("S"));
    }

    @Test
    public void should_return_mower_position_with_turnLeft() throws InvalidGardenSizeException, MowerInitialPositionException, IOException, FileFormatInvalidException {
        // Given
        MowerService mowerService = new MowerService(baseDirForTestData + "TurnLeft.txt");

        // When
        List<Position> results = mowerService.launchMowers();

        // Then
        assertEquals(results.size(), 1);
        assertEquals(results.get(0).getX(), 1);
        assertEquals(results.get(0).getY(), 1);
        assertEquals(results.get(0).getDirection(), Direction.fromCode("W"));
    }

    @Test
    public void should_return_mower_position_with_goAhead() throws InvalidGardenSizeException, MowerInitialPositionException, IOException, FileFormatInvalidException {
        // Given
        MowerService mowerService = new MowerService(baseDirForTestData + "GoAhead.txt");

        // When
        List<Position> results = mowerService.launchMowers();

        // Then
        assertEquals(results.size(), 1);
        assertEquals(results.get(0).getX(), 1);
        assertEquals(results.get(0).getY(), 2);
        assertEquals(results.get(0).getDirection(), Direction.fromCode("N"));
    }

    @Test
    public void should_return_mower_position_with_goAhead_and_passLimit() throws InvalidGardenSizeException, MowerInitialPositionException, IOException, FileFormatInvalidException {
        // Given
        MowerService mowerService = new MowerService(baseDirForTestData + "GoAheadAndPassLimit.txt");

        // When
        List<Position> results = mowerService.launchMowers();

        // Then
        assertEquals(results.size(), 1);
        assertEquals(results.get(0).getX(), 2);
        assertEquals(results.get(0).getY(), 2);
        assertEquals(results.get(0).getDirection(), Direction.fromCode("N"));
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