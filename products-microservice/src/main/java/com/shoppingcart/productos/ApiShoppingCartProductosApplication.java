package com.shoppingcart.productos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication
public class ApiShoppingCartProductosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiShoppingCartProductosApplication.class, args);
	}

}
