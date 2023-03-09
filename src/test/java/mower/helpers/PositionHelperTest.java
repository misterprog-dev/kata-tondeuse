package mower.helpers;

import mower.exception.FileFormatInvalidException;
import mower.exception.InvalidGardenSizeException;
import mower.exception.MowerInitialPositionException;
import mower.models.Position;
import mower.services.MowerService;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static mower.Constant.baseDirForTestData;
import static org.junit.Assert.*;

public class PositionHelperTest {
    @Test
    public void should_return_exception_for_invalid_mowerInitalPosition() {
        // when
        Exception exception = assertThrows(MowerInitialPositionException.class, () -> PositionHelper.getMowerPosition("1 2"));

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
}
