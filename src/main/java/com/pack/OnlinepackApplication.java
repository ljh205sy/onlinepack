package com.pack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class OnlinepackApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlinepackApplication.class, args);
	}

}
