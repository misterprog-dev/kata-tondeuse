package mower;


import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class MowerTest {
    @Test
    public void shoud_Return_exception_When_FileDontExist() {
        // given
        Mower mower = new Mower();

        // when
        Exception exception = assertThrows(FileNotFoundException.class, mower::process);

        // then
            String expectedMessage = "File not exists";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
