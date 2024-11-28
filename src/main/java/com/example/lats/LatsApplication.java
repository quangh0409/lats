package com.example.lats;

import io.hypersistence.utils.spring.repository.BaseJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.example.lats"})
@EntityScan("com.example.lats.model.entity")
@EnableJpaRepositories(value = "com.example.lats.repository",
		repositoryBaseClass = BaseJpaRepositoryImpl.class)

public class LatsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LatsApplication.class, args);
	}

}

