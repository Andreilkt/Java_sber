package org.example;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

public class Main2 {

    public static void main(String[] args) {
        General general = new General("GBK", "/Регресс/ЗОД и Отчетность/3 Отчетность/3.4 Книга регистрации/test2", "TEST_CASE");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization","Basic c2F2Y2h1ay1hZzpQQVJBcGxhbjchQCM=");

        RestTemplate restTemplate = new RestTemplate();

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        restTemplate.setMessageConverters(Collections.singletonList(converter));
        // Data attached to the request.
        HttpEntity<General> requestBody = new HttpEntity<>(general, headers);

        // Send request with POST method.
        String e = restTemplate.postForObject("https://jira.delta.sbrf.ru/rest/atm/1.0/folder", requestBody, String.class);

        System.out.println(e);


    }

}
