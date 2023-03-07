package mower;


import mower.exception.FileFormatInvalidException;
import org.junit.Test;

import java.io.FileNotFoundException;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class MowerServiceTest {
    @Test
    public void shoud_Return_exception_When_FileDontExist() {
        // when
        Exception exception = assertThrows(FileNotFoundException.class, () -> new MowerService(null));

        // then
        String expectedMessage = "File not exists";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shoud_Return_exception_When_FileFormatIsNotCorrect() {
        // when
        Exception exception = assertThrows(FileFormatInvalidException.class, () -> new MowerService(asList()));

        // then
        String expectedMessage = "File format invalid";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
