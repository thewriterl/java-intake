package com.example.javaintake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan({"com.example.javaintake.utils.components"})
public class JavaIntakeApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaIntakeApplication.class, args);
	}

}
