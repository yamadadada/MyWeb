package com.example.getrealpopular;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@ServletComponentScan
public class GetrealpopularApplication {

    public static void main(String[] args) {
        SpringApplication.run(GetrealpopularApplication.class, args);
    }

}

