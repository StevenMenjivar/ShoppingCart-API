package com.shoppingcart.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan({"com.shoppingcart.commons.users.models.entity"})
@SpringBootApplication
public class UsersMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersMicroserviceApplication.class, args);
	}

}
