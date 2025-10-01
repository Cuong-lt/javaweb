package com.javaweb.batdongsan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BatdongsanApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatdongsanApplication.class, args);
	}

}
