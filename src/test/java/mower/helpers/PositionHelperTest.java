package mower.helpers;

import mower.exception.MowerInitialPositionException;
import mower.models.Direction;
import mower.models.Position;
import org.junit.Test;

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
    public void should_return_position_for_mower() throws MowerInitialPositionException {
        // When
        Position result = PositionHelper.getMowerPosition("3 2 E");

        // Then
        assertEquals(result, new Position(3, 2, Direction.EAST));
    }
}
