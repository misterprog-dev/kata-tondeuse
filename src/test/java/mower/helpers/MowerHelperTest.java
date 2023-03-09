package mower.helpers;

import mower.exception.MowerInitialPositionException;
import mower.models.Command;
import mower.models.LimitGarden;
import mower.models.Mower;
import mower.models.Position;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static mower.models.Direction.NORTH;
import static org.junit.Assert.assertEquals;

public class MowerHelperTest {
    @Test
    public void should_constructMower_WithLimitGardenAndPositionAndCommand() throws MowerInitialPositionException {
        // Given
        Iterator<String> fileLinesIterator = asList("1 2 N", "GAGAGAGA", "3 3 E", "AADAAGAGA").iterator();

        // when
        List<Mower> mowers = MowerHelper.constructMower(fileLinesIterator, new LimitGarden(5, 5));

        // then
        assertEquals(mowers.size(), 2);
        Mower firstMower = mowers.get(0);
        assertEquals(firstMower.getPosition(), new Position(1, 2, NORTH));
        assertEquals(firstMower.getCommands().size(), 8);
        List<String> commandsValue = firstMower.getCommands().stream()
                .map(Command::getCode)
                .collect(toList());
        assertEquals(commandsValue, asList("G","A", "G", "A", "G", "A", "G", "A"));
    }
}
