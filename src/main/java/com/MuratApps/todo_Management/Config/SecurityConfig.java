package com.MuratApps.todo_Management.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.apache.catalina.webresources.TomcatURLStreamHandlerFactory.disable;


@Configuration
public class SecurityConfig {

     @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception{
      http.csrf(CsrfConfigurer::disable).authorizeHttpRequests((autherize)->{
            autherize.anyRequest().authenticated();
        }).httpBasic(Customizer.withDefaults());
        return http.build();
     }



}
