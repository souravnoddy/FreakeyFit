package com.freaky.fit;

import com.freaky.fit.healthcheck.SmokeTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FreakyFitApplication implements CommandLineRunner {
    @Autowired
    private SmokeTest smokeTest;

    public static void main(String[] args) {
        SpringApplication.run(FreakyFitApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        smokeTest.createDummyUserDetails();
    }
}
