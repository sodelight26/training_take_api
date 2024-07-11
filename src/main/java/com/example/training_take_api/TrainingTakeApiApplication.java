package com.example.training_take_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.data.convert.ReadingConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@SpringBootApplication
public class TrainingTakeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainingTakeApiApplication.class, args);
	}


}
