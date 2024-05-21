package com.shoppingcart.productos;

import com.shoppingcart.productos.models.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class ApiShoppingCartProductosApplication implements CommandLineRunner{
	@Autowired
	private IProductService productService;

	public static void main(String[] args) {
		SpringApplication.run(ApiShoppingCartProductosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		productService.getAllProductsFromFakeStore();
	}
}
