package com.shoppingcart.orders;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class APPConfig {
    @Bean("clientRest")
    @LoadBalanced
    public RestTemplate registerRestTemplate(){
        return new RestTemplate();
    }
}
