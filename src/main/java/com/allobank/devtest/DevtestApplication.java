package com.allobank.devtest;

import com.allobank.devtest.config.ApiProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Slf4j
@SpringBootApplication
@EnableConfigurationProperties(ApiProperties.class)
public class DevtestApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevtestApplication.class, args);
    }

}
