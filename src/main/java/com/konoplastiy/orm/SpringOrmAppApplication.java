package com.konoplastiy.orm;

import com.github.javafaker.Faker;
import com.konoplastiy.orm.entities.*;
import com.konoplastiy.orm.repositories.StudentIdCardRepository;
import com.konoplastiy.orm.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class SpringOrmAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringOrmAppApplication.class, args);
	}

}
