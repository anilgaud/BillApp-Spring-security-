package com.bill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "com.bill.")
public class SimpleApplication extends ServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SimpleApplication.class, args);
	}

}
