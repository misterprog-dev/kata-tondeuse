package mower;


import mower.exception.FileFormatInvalidException;
import mower.exception.InvalidGardenSizeException;
import org.junit.Test;

import java.io.FileNotFoundException;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

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
}
