package com.group6.webbportal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebbPortalApplication {

    public static final Logger logger = LogManager.getLogger("myLogger");
    public static void main(String[] args) {
        SpringApplication.run(WebbPortalApplication.class, args);
    }

}
