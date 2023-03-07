package com.nnk.springboot.configuration;

import com.nnk.springboot.services.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;

    }

   // PasswordEncoder passwordEncoder;
    UserDetailsServiceImpl userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //page Home accessible à tous même sans connexion
      //  http.csrf().disable().authorizeRequests().antMatchers("/", "/home/**",  "/login/**").permitAll();
        // attention aux accès aux répertoires static qui contiennent les css!! Si on
        // donne l accès au menu home à tt le monde par ex...
    //    http.csrf().disable().authorizeRequests().antMatchers("/webjars/**","/media/**","/css/**" ).permitAll();

        //autres pages : utilisateur doit être authenticated();
        http
                .csrf().disable().authorizeHttpRequests(authorize -> {
                    try {
                        authorize
                                .requestMatchers("/", "/admin/home", "/home/*",  "/login/*", "/loginWithUserPwd/**", "/static/css/*","org/webjars/*").permitAll()
                                .requestMatchers( "bidList/*", "curvePoint/*", "rating/*", "ruleName/*").hasAnyRole("ADMIN","USER")
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
                                .deleteCookies("JSESSIONID");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
              //  .antMatchers().permitAll()
             //   .antMatchers( "/webjars/**","/media/**","/css/**" ).permitAll()
             //   .antMatchers("../")
            //    .antMatchers("/user/**").hasAuthority("ADMIN")


               // .loginPage("/login")
              //  .defaultSuccessUrl("/user/list")
        ;

          //  http.authenticationManager(authenticationManager(userDetailsService));
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





}
