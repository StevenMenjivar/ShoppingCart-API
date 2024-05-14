package com.shoppingcart.items;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

//REST TEMPLATE
//items-microservicio
@Configuration
public class APPConfig {
    //Cliente Rest HTTP para acceder a recursos de otros microservicios
    @Bean("clientRest")
    @LoadBalanced
    public RestTemplate registerRestTemplate(){
        return new RestTemplate();
    }
}
