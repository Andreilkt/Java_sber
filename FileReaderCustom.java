package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class FileReaderCustom {
    public static final String path = "E:\\DevTransfer\\reload\\test_spel\\src\\main\\resources\\";
    public static Map<String, String> getDgeneralParams() throws Exception {
       try(FileInputStream fileInputStream = new FileInputStream(path + "general.txt")){
           byte[] bufeer = new byte[fileInputStream.available()];
           fileInputStream.read(bufeer,0,bufeer.length);
           String str = new String( bufeer, "UTF-8");
           ObjectMapper mapper = new ObjectMapper();
           Map <String,String>  map = mapper.readValue(str, Map.class);
           return map;
       }
    }

    public static byte[] getParams(Map<String,String> params) throws Exception{
        StringBuilder requestData = new StringBuilder();
        for (Map.Entry<String,String> param : params.entrySet()) {
            if (requestData.length() != 0) {
                requestData.append('&');
            }
            // Encode the parameter based on the parameter map we've defined
            // and append the values from the map to form a single parameter
            requestData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            requestData.append('=');
            requestData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
// Convert the requestData into bytes
        byte[] requestDataByes = requestData.toString().getBytes("UTF-8");
        return requestDataByes;
    }

    public static List<String> getLines() throws Exception {
        List<String> result = new ArrayList<>();
        File file = new File(path  + "lines.txt");
        //создаем объект FileReader для объекта File
        FileReader fr = new FileReader(file);
        //создаем BufferedReader с существующего FileReader для построчного считывания
        BufferedReader reader = new BufferedReader(fr);
        // считаем сначала первую строку
        String line = reader.readLine();
        while (line != null) {
            result.add(line);
            line = reader.readLine();
        }
        return result;
    }
}
