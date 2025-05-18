package com.example.canvac1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.example.canvac1.config.JwtProperties;

@SpringBootApplication
@EnableConfigurationProperties({JwtProperties.class})
public class Canvac1Application {

	public static void main(String[] args) {
		System.out.println("Starting Canvac1Application...");
		SpringApplication.run(Canvac1Application.class, args);
	}

}
