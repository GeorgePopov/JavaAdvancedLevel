package lesson2_Exceptions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ReadFileTest {
    public static void main(String[] args) {
        List<String> lines; // = null

        try {
            lines = Files.readAllLines(Paths.get("test.txt")); // здесь указывается путь, т.к. файл лежит в корне
                                                                    // самого проекта, пишим просто имя файла
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("caught error!");
            e.printStackTrace();
        }
    }
}
