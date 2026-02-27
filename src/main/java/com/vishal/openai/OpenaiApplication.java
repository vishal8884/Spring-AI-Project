package com.vishal.openai;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class OpenaiApplication {

	@Autowired
	ApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(OpenaiApplication.class, args);
	}


	@PostConstruct
	public void printBeans() {
		Arrays.stream(context.getBeanDefinitionNames())
				.forEach(System.out::println);
	}

}
