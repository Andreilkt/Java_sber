package ru.cucumber;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Считывание файла ".future" из
 *
 * Запись файла Cucumber в после добавления аннотаций к строке с названием сценария
 * @author Savchuk Andrei
 */
public class FileReaderCucumber {
    public static final String path = "E:\\DevTransfer\\reload\\test_spel\\src\\main\\resources\\";
    // построчное считывание файла
    public static List<String> getLines() throws Exception  {
       {
            List<String> result = new ArrayList<>();
            File file = new File(path  + "2.1.1 Дооценка счетов.feature");
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line = reader.readLine();
            while (line != null) {
                result.add(line);
                System.out.println(line);
                line = reader.readLine();
            }
            return result;
        }
    }

    public static void writelinstofile(List<String> lines)
    {
        try
        {
            OutputStream f = new FileOutputStream(path  + "File.txt", true);
            OutputStreamWriter writer = new OutputStreamWriter(f);
            BufferedWriter out = new BufferedWriter(writer);
            for(int i = 0; i < lines.size(); i++)
            {
                out.write(lines.get(i) + "\n");
                out.flush();
            }
        }
        catch(IOException ex)
        {
            System.err.println(ex);
        }
    }
}

