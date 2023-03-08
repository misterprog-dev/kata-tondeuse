package mower;

import mower.exception.FileFormatInvalidException;
import mower.exception.InvalidGardenSizeException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.util.Arrays.stream;
import static org.apache.commons.lang3.StringUtils.isNumeric;

public class MowerService {
    private final List<Mower> mowers = new ArrayList<>();

    public MowerService(List<String> fileLines) throws FileNotFoundException, FileFormatInvalidException, InvalidGardenSizeException {
        ValidateFileContent(fileLines);

        Iterator<String> fileLinesIterator = fileLines.iterator();
        String[] firstLineOfFile = " ".split(fileLinesIterator.next());

        if (stream(firstLineOfFile).count() < 2 || !isNumeric(firstLineOfFile[0]) || !isNumeric(firstLineOfFile[1])) {
            throw new InvalidGardenSizeException("Garden size is invalid");
        }
    }

    private static void ValidateFileContent(List<String> fileLines) throws FileNotFoundException, FileFormatInvalidException {
        if (fileLines == null) {
            throw new FileNotFoundException("File not exists");
        }
        if (fileLines.size() < 3 || fileLines.size() % 2 == 0) { // % 2 : each mower have 2 lines that concern it + first line to garden size.
            throw new FileFormatInvalidException("File format invalid");
        }
    }

}
