package pl.sobczak.swapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SwTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwTestApplication.class, args);
    }

}
