package com.shoppingcart.zuul.oauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;


@Configuration
@EnableResourceServer
public class ResourceServerConf extends ResourceServerConfigurerAdapter {
    public ResourceServerConf() {
        super();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore());
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        //Endpoints restrictions by user role
        http.authorizeRequests().antMatchers("/api/security/oauth/**").permitAll()//autentication, all users can do it
                .antMatchers(HttpMethod.GET, "/api/products/products","/api/products/products/view/{id}","/api/products/products/get-products-fakestore","/api/orders/orders","/api/payments/payments","/api/payments/payments/{id}","/api/orders/orders/{id}","/api/users/users/","/api/users/users/{id}").permitAll()
                .antMatchers(HttpMethod.POST,"/api/products/create-product","/api/orders/create-order","/api/payments/pay-order","/api/users/users/").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/api/orders/create-order","/api/payments/pay-order").hasRole("USER")

                .antMatchers(HttpMethod.PUT,"/api/products/update-product/{id}","/api/users/users/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/api/orders/orders/{id}","/api/products/delete-product/{id}","/api/users/users/{id}").hasRole("ADMIN")
                .anyRequest().authenticated();


    }
    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter tokenConverter=new JwtAccessTokenConverter();
        //token-signature
        tokenConverter.setSigningKey("some_secret");
        return tokenConverter;
    }
}
