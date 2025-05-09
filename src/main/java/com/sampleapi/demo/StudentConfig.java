package com.sampleapi.demo;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student maria = new Student(
                    "Adam",
                    "adam12@gmail.com",
                    LocalDate.of(2000, Month.JANUARY, 5)
            );
            Student alex = new Student(
                    "Alex",
                    "alex12@gmail.com",
                    LocalDate.of(2000, Month.JANUARY, 5)
            );

            repository.saveAll(List.of(maria, alex));
        };
    }
}
    
