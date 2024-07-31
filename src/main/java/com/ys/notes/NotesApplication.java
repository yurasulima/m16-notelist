package com.ys.notes;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotesApplication {

    public static void main(String[] args) {

        SpringApplication.run(NotesApplication.class, args);
    }
}
