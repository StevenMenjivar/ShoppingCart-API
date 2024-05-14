package com.shoppingcart.oauth.services;

import com.shoppingcart.commons.users.models.entity.User;
import com.shoppingcart.oauth.clients.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//CLASE ENCARGADA DE AUTENTICAR
@Service
public class UserService implements IUserService,UserDetailsService {
    private Logger log= LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserFeignClient client;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=client.findByUsername(username);

        if(user==null){
            log.error("User '"+username+"' don't exist.");
            throw new UsernameNotFoundException("User '"+username+"' don't exist.");
        }

        List<GrantedAuthority> authorities =user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .peek(authority->log.info("Role: " +authority.getAuthority()))
                .collect(Collectors.toList());
        log.info("Authenticated user "+username);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getEnabled(),true,true,true,authorities);
    }

    //OBTENIENDO LOS DATOS DE USUARIO
    @Override
    public User findByUsername(String username) {
        return client.findByUsername(username);
    }
}
