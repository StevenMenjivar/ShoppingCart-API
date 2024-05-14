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
        //PROTEGIENDO ENDPOINTS
        http.authorizeRequests().antMatchers("/api/security/oauth/**").permitAll()//autenticar
                .antMatchers(HttpMethod.GET, "/api/products/listar","/api/items/listar","/api/users/users/").permitAll()
                .antMatchers(HttpMethod.GET, "/api/products/ver/{id}","/api/items/ver/{id}/quantity/{quantity}","/api/users/users/{id}").hasAnyRole("ADMIN","USER")
                .antMatchers(HttpMethod.POST,"/api/products/crear","/api/items/ver","/api/users/users/").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/products/editar/{id}","/api/items/editar/{id}","/api/users/users/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/api/products/eliminar/{id}","/api/items/eliminar/{id}","/api/users/users/{id}").hasRole("ADMIN")
                .anyRequest().authenticated();


    }
    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter tokenConverter=new JwtAccessTokenConverter();
        //firma de token
        tokenConverter.setSigningKey("some_secret");
        return tokenConverter;
    }
}
