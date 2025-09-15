package com.HAM_application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;



@SpringBootApplication
@EntityScan("com.HAM_application.dao.entity")
public class HAMApplication6 {
    public static void main(String[] args) {
        SpringApplication.run(HAMApplication6.class, args);
    }
}
