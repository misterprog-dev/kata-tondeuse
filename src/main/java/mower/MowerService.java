package mower;


import mower.exception.FileFormatInvalidException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MowerService {
    private final List<Mower> mower = new ArrayList<Mower>();

    public MowerService(List<String> fileLines) throws FileNotFoundException, FileFormatInvalidException {
        if (fileLines == null) {
            throw new FileNotFoundException("File not exists");
        }
        if (fileLines.size() == 0) {
            throw new FileFormatInvalidException("File format invalid");
        }
    }

}
