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

import java.io.FileNotFoundException;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.*;

public class MowerServiceTest {
    @Test
    public void should_Return_exception_When_FileDontExist() {
        // when
        Exception exception = assertThrows(FileNotFoundException.class, () -> new MowerService(null));

        // then
        String expectedMessage = "File not exists";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void should_Return_exception_When_FileFormatIsNotCorrect() {
        // when
        Exception exception = assertThrows(FileFormatInvalidException.class, () -> new MowerService(asList()));

        // then
        String expectedMessage = "File format invalid";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void should_return_exception_for_invalid_garden() {
        // when
        Exception exception = assertThrows(InvalidGardenSizeException.class, () -> new MowerService(asList("5 ", "1 2 N", "GAGAGAGA", "3 3 E", "AADAAGAGA")));

        // then
        String expectedMessage = "Garden size is invalid";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void should_constructMower_WithLimitGardenAndPositionAndCommand() throws InvalidGardenSizeException, FileNotFoundException, FileFormatInvalidException, MowerInitialPositionException {
        // when
        MowerService mowerService = new MowerService(asList("5 5", "1 2 N", "GAGAGAGA", "3 3 E", "AADAAGAGA"));

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
        Exception exception = assertThrows(MowerInitialPositionException.class, () -> new MowerService(asList("5 5", "1 2", "GAGAGAGA")));

        // then
        String expectedMessage = "Invalid position for Mower";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void should_return_position_for_all_mower() throws InvalidGardenSizeException, MowerInitialPositionException, FileNotFoundException, FileFormatInvalidException {
        // Given
        MowerService mowerService = new MowerService(asList("5 5", "1 2 N", "GAGAGAGAA", "3 3 E", "AADAADADDA"));

        // When
        List<Position> results = mowerService.launchMowers();

        // Then
        assertEquals(results.size(), 2);
    }

    @Test
    public void should_return_mower_position_with_turnRight() throws InvalidGardenSizeException, MowerInitialPositionException, FileNotFoundException, FileFormatInvalidException {
        // Given
        MowerService mowerService = new MowerService(asList("5 5", "1 2 N", "DD"));

        // When
        List<Position> results = mowerService.launchMowers();

        // Then
        assertEquals(results.size(), 1);
        assertEquals(results.get(0).getX(), 1);
        assertEquals(results.get(0).getY(), 2);
        assertEquals(results.get(0).getDirection(), Direction.fromCode("S"));
    }

    @Test
    public void should_return_mower_position_with_turnLeft() throws InvalidGardenSizeException, MowerInitialPositionException, FileNotFoundException, FileFormatInvalidException {
        // Given
        MowerService mowerService = new MowerService(asList("2 2", "1 1 N", "GGGGG"));

        // When
        List<Position> results = mowerService.launchMowers();

        // Then
        assertEquals(results.size(), 1);
        assertEquals(results.get(0).getX(), 1);
        assertEquals(results.get(0).getY(), 1);
        assertEquals(results.get(0).getDirection(), Direction.fromCode("W"));
    }

    @Test
    public void should_return_mower_position_with_goAhead() throws InvalidGardenSizeException, MowerInitialPositionException, FileNotFoundException, FileFormatInvalidException {
        // Given
        MowerService mowerService = new MowerService(asList("2 2", "1 1 N", "AAA"));

        // When
        List<Position> results = mowerService.launchMowers();

        // Then
        assertEquals(results.size(), 1);
        assertEquals(results.get(0).getX(), 1);
        assertEquals(results.get(0).getY(), 4);
        assertEquals(results.get(0).getDirection(), Direction.fromCode("N"));
    }
}