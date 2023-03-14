package com.example.gruppebjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class GruppeBJavaApplication extends SpringBootServletInitializer {
    //GruppeBJavaApplication
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(GruppeBJavaApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(GruppeBJavaApplication.class, args);
    }

}
