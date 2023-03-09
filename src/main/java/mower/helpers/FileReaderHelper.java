package mower.helpers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReaderHelper {
    public static List<String> readFile(String filePath) throws IOException {
        List<String> result = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            reader.lines().forEach(result::add);
            reader.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found");
        }
        return result;
    }
}
