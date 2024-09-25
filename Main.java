package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        Map<String, String> params = FileReaderCustom.getDgeneralParams();
        for (String line : FileReaderCustom.getLines()) {
            HttpURLConnection connection = getConnection();
            int responseCode = sendByLine(connection, params, line.trim());
            if (responseCode != 201) {
                break;
            }
            Thread.sleep(1000);
        }
    }

    private static int sendByLine(HttpURLConnection connection, Map<String, String> params, String nameValue) throws Exception {
        try (OutputStream writer = connection.getOutputStream()) {
            //params.put("name", folder + nameValue);
            params.put("name", nameValue);
            String str = new ObjectMapper().writeValueAsString(params);
            byte[] out = str.getBytes(StandardCharsets.UTF_8);
            writer.write(out);
            writer.flush();
            writer.close();
        }
        StringBuilder content;
        try (BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            content = new StringBuilder();
            while ((line = input.readLine()) != null) {
                content.append(line);
                content.append(System.lineSeparator());
            }
            System.out.println(content);
        } finally {
            connection.disconnect();
            return connection.getResponseCode();
        }
    }

    private static HttpURLConnection getConnection() throws Exception {
        URL url = new URL("https://jira.delta.sbrf.ru/rest/atm/1.0/testcase");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Authorization", "Basic c2F2Y2h1ay1hZzpQQVJBcGxhbjchQCM=");
        connection.setConnectTimeout(10000);
        connection.setReadTimeout(10000);
        connection.setDoOutput(true);
        return connection;
    }
}