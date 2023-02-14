package com.nnk.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
//@ComponentScan({"com.nnk.springboot.controllers", "com.nnk.springboot.domain" , "com.nnk.springboot.exceptions", "com.nnk.springboot.repositories", "com.nnk.springboot.services"})
public class PoseidonApplication {

     public static void main(String[] args) {
        SpringApplication.run(PoseidonApplication.class, args);
    }
    // BCrypt pour cryptage du mot de passe
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
