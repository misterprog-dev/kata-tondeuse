package mower;

import java.io.FileNotFoundException;

public class Mower {

    public void process() throws FileNotFoundException {
        throw new FileNotFoundException("File not exists");
    }
}
