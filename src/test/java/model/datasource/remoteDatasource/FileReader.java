package model.datasource.remoteDatasource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class FileReader {
    public static String readStringFromFile(String filename) {
        InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(filename);
        return new BufferedReader(new InputStreamReader(inputStream))
            .lines().collect(Collectors.joining("\n"));
    }
}