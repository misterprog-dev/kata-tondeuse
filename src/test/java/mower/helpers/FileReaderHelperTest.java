package mower.helpers;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class FileReaderHelperTest {
    @Test
    public void shouldReturnExceptionWhenFileNotFound() {
        // when
        Exception exception = assertThrows(FileNotFoundException.class, () -> FileReaderHelper.readFile("///"));

        // then
        String expectedMessage = "File not found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldReturnFileContentAsString() throws IOException {
        // When
        List<String> result = FileReaderHelper.readFile("src/test/resources/data/DisplayFinalResult.txt");

        // Then
        assertEquals(result, asList("5 5", "1 2 N", "GAGAGAGAA", "3 3 E", "AADAADADDA"));
    }
}
