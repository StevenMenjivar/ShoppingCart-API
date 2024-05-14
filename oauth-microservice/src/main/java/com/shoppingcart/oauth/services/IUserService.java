package com.shoppingcart.oauth.services;

import com.shoppingcart.commons.users.models.entity.User;

public interface IUserService {
    public User findByUsername(String username);

}
