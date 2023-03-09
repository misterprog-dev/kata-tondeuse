package mower.helpers;

import mower.exception.InvalidGardenSizeException;
import mower.models.Garden;
import org.junit.Test;

import static org.junit.Assert.*;

public class GardenHelperTest {
    @Test
    public void should_return_exception_for_invalid_garden() {
        // Given
        String[] line = {"5"};

        // when
        Exception exception = assertThrows(InvalidGardenSizeException.class, () -> GardenHelper.getLimitGarden(line));

        // then
        String expectedMessage = "Garden size is invalid";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void should_return_exception_for_no_numeric_garden_size() {
        // Given
        String[] line = {"A 5"};

        // when
        Exception exception = assertThrows(InvalidGardenSizeException.class, () -> GardenHelper.getLimitGarden(line));

        // then
        String expectedMessage = "Garden size is invalid";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shoudl_return_LimitGarden_for_correctGarded() throws InvalidGardenSizeException {
        // Given
        String[] fileLines = {"5","3"};

        // When
        Garden garden = GardenHelper.getLimitGarden(fileLines);

        // Then
        assertEquals(garden.getX(), 5);
        assertEquals(garden.getY(), 3);
    }
}
