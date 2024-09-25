package ru.cucumber;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileInputStream;
import java.util.Map;
import java.io.*;
import java.util.*;

/**
 * Считывание файла с тест-кейсами  из Jira
 * Добавление "@TestCaseKey=" в map для последующей записи в файл ".future"
 *
 * @author Savchuk Andrei
 */
class FileReaderJira {
        public static Map<String, String> HashMapFromTextFile(String path)
    {
        Map<String, String> map = new HashMap<String, String>();
        BufferedReader br = null ;
        try {
            File file = new File(path);
// create BufferedReader object from the File
            br = new BufferedReader( new FileReader(file));
            String line = null ;
// read file line by line
            while ((line = br.readLine()) != null ) {
// split the line by :
                    String[] arrline =  line.split("\t");
                    String key =arrline[1].trim() ;
                    String value ="@TestCaseKey="  + arrline[0].trim();
                    // put name, number in HashMap if they are not empty
                if (!key.equals( "" ) && !value.equals( "" ))
                      map.put(key, value);
                      System.out.println(map);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
// Always close the BufferedReader
            if (br != null ) {
                try {
                    br.close();
                }
                catch (Exception e) {
                };
            }
        }
        return map;
    }
}
