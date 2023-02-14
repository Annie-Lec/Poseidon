package com.nnk.springboot.configuration;

import com.nnk.springboot.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    @Autowired
    PasswordEncoder passwordEncoder;
    UserDetailsServiceImpl userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //page Home accessible à tous même sans connexion
      //  http.csrf().disable().authorizeRequests().antMatchers("/", "/home/**",  "/login/**").permitAll();
        // attention aux accès aux répertoires static qui contiennent les css!! Si on
        // donne l accès au menu home à tt le monde par ex...
    //    http.csrf().disable().authorizeRequests().antMatchers("/webjars/**","/media/**","/css/**" ).permitAll();

        //autres pages : utilisateur doit être authenticated();
        http.csrf().disable()
                .authorizeRequests()
              // .antMatchers("/user/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
               // .loginPage("/login")
                //.successForwardUrl("/")
                .and()
                .oauth2Login().permitAll();

        http.authenticationProvider(authenticationProvider());
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }



}
