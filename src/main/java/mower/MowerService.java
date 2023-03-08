package mower;


import mower.exception.FileFormatInvalidException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MowerService {
    private final List<Mower> mower = new ArrayList<Mower>();

    public MowerService(List<String> fileLines) throws FileNotFoundException, FileFormatInvalidException {
        ValidateFileContent(fileLines);
    }

    private static void ValidateFileContent(List<String> fileLines) throws FileNotFoundException, FileFormatInvalidException {
        if (fileLines == null) {
            throw new FileNotFoundException("File not exists");
        }
        if (fileLines.size() < 3 || fileLines.size() % 2 != 0) { // % 2 : each mower have 2 lines that concern it + first line to garden size.
            throw new FileFormatInvalidException("File format invalid");
        }
    }

}
