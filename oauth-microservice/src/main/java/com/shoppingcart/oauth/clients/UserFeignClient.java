package com.shoppingcart.oauth.clients;

import com.shoppingcart.commons.users.models.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "users-microservice")
public interface UserFeignClient {
    //CONSUMIENDO USER MICROSERVICE
    @GetMapping("/users/search/search-username")
    public User findByUsername(@RequestParam String username);



}
