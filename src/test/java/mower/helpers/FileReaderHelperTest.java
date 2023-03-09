package mower.helpers;

import mower.exception.FileFormatInvalidException;
import mower.services.MowerService;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;
import static mower.Constant.baseDirForTestData;
import static mower.helpers.FileReaderHelper.readFile;
import static org.junit.Assert.*;

public class FileReaderHelperTest {
    @Test
    public void shouldReturnExceptionWhenFileNotFound() {
        // when
        Exception exception = assertThrows(FileNotFoundException.class, () -> readFile("///"));

        // then
        String expectedMessage = "File not found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldReturnFileContentAsString() throws IOException, FileFormatInvalidException {
        // When
        List<String> result = readFile(baseDirForTestData + "DisplayFinalResult.txt");

        // Then
        assertEquals(result, asList("5 5", "1 2 N", "GAGAGAGAA", "3 3 E", "AADAADADDA"));
    }

    @Test
    public void should_Return_exception_When_FileFormatIsNotCorrect() {
        // when
        Exception exception = assertThrows(FileFormatInvalidException.class, () -> new MowerService(baseDirForTestData + "IncorrectFormat.txt"));

        // then
        String expectedMessage = "File format invalid";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
