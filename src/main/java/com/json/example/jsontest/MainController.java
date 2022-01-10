package com.json.example.jsontest;

import org.json.simple.parser.JSONParser;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@RestController
@Slf4j
@RequestMapping("/api")
public class MainController {


    @GetMapping("/get")
    public ResponseEntity<?> getJsonData() throws NullPointerException, ParseException, IOException {
        JSONParser parser = new JSONParser();
        StringBuffer response = null;

        response = null;
        String uri = "https://1lzur2qul1.execute-api.us-east-2.amazonaws.com/prod/getLatestTransactions?skip=0&limit=1";
        URL obj = new URL(uri);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Opera");
        int responseCode = con.getResponseCode();
        log.info("Sending 'GET' Request To Url : " + uri);
        log.info("Response Code : " + responseCode);
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        response = new StringBuffer();

        while ((inputLine = br.readLine()) != null) {
            response.append(inputLine);
        }
        br.close();


        JSONObject myresponse = new JSONObject(response.toString());
        String message = (String) myresponse.get("message");
        int response_code = (int) myresponse.get("responseCode");
        boolean success = (boolean) myresponse.get("success");
        String val = null;

        Object object = parser.parse(myresponse.toString());
        org.json.simple.JSONObject objectValue = (org.json.simple.JSONObject) object;
        org.json.simple.JSONArray jsonArray = (org.json.simple.JSONArray) objectValue.get("responseData");

        for (Object o : jsonArray) {
            org.json.simple.JSONObject value = (org.json.simple.JSONObject) o;
            val = (String) value.get("value");
        }

        log.info("message : " + myresponse.get("message"));
        log.info("response_code : " + response_code);
        log.info("success : " + success);
        log.info("value : " + val);


        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("response_code", response_code);
        map.put("success", success);
        map.put("value", val);

        Map<String, Object> mapAll = new HashMap<>();
        mapAll.put("data", map);
        return new ResponseEntity<>(mapAll, HttpStatus.OK);
    }

}
