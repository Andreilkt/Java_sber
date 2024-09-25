package ru.cucumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static ru.cucumber.FileReaderCucumber.getLines;
import static ru.cucumber.FileReaderCucumber.writelinstofile;
import static ru.cucumber.FileReaderJira.HashMapFromTextFile;

/**
 * Считывание списка созданного в файле FileReaderCucumber, из ".future" из
 *
 * Выбор строк содержащих подстроку "Сценарий" для добавления "@TestCaseKey="
 * @author Savchuk Andrei
 */
public class FileWriteCucumber {
    public static final String path = "E:\\DevTransfer\\reload\\test_spel\\src\\main\\resources\\";

    public static void main(String[] args) throws Exception {
        List<String> fromfile = getLines();
        Map<String, String> diffmap = HashMapFromTextFile("E:\\DevTransfer\\reload\\test_spel\\src\\main\\resources\\jira.txt");
        List<String> result = new ArrayList<>();
        for (String line : fromfile
        ) {
            if (line.contains("Сценарий")) {
                String[] arraycucumber = line.split(":");
                String namecucumber = arraycucumber[1].trim();
                String testkeycucumber = diffmap.get(namecucumber);
                int indexcucumber = line.indexOf("Сценарий");
                result.add(getTestKeyCucumberWithSpaces(testkeycucumber, indexcucumber));}
            result.add(line);
        }
        writelinstofile(result);
    }

    private static String getTestKeyCucumberWithSpaces(String testKeyCucumber, int indexCucumber) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indexCucumber; i++) {
            sb.append(" ");
        }
        sb.append(testKeyCucumber);
        return sb.toString();
    }
}









