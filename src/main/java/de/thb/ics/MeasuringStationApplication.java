package de.thb.ics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class MeasuringStationApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeasuringStationApplication.class, args);
    }

    @PostConstruct
    public void init() {
        System.setProperty("org.jooq.no-logo", "true");
    }
}
