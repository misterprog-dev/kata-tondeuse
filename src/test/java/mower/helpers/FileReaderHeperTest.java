package mower.helpers;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class FileReaderHeperTest {
    @Test
    public void shouldReturnExceptionWhenFileNotFound() {
        // when
        Exception exception = assertThrows(FileNotFoundException.class, () -> FileReaderHelper.readFile("///"));

        // then
        String expectedMessage = "File not found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
