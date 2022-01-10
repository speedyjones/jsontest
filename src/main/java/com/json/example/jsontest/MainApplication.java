package com.json.example.jsontest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class MainApplication {





    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);

        log.info("Application Started......................................");
    }


}
