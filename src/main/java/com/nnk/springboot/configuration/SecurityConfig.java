package com.nnk.springboot.configuration;

import com.nnk.springboot.services.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {

    public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    UserDetailsServiceImpl userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable().authorizeHttpRequests(authorize -> {
                    try {
                        authorize
                                .requestMatchers("/", "/admin/home", "/home/*",  "/login/*", "/loginWithUserPwd/**", "/static/css/*","org/webjars/*").permitAll()
                                .requestMatchers( "/bidList/*", "/curvePoint/*", "/rating/*", "/trade/*",  "/ruleName/*").hasAnyRole("USER")
                                .requestMatchers("/user/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                                .and()
                                .formLogin()
                                .loginPage("/loginWithUserPwd")
                                .successForwardUrl("/")
                                .and()
                                    .oauth2Login().defaultSuccessUrl("/")
                                 .and()
                                .logout()
                                .deleteCookies("JSESSIONID")
                                .and()
                                    .oauth2Login().userInfoEndpoint().userAuthoritiesMapper(userAuthoritiesMapper());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });

        http.authenticationProvider(authenticationProvider());
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    //@Bean
    public AuthenticationManager authenticationManager(UserDetailsServiceImpl userDetailsService)
            throws Exception {

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(authProvider);
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public GrantedAuthoritiesMapper userAuthoritiesMapper() {
        return (authorities) -> {
            Set<GrantedAuthority> mappedAuthorities = new HashSet<>();

            mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            log.info("The user authorities with GITHUB: {}", mappedAuthorities);
            return mappedAuthorities;
        };
    }

}
