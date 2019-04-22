package com.tinklabs.handy.logs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.tinklabs.handy.*"})
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
