package com.nnk.springboot.services;

import com.nnk.springboot.domain.AppUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserService userService;
    //injection des dépendences
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = userService.loadUserByUsername(username);
            if(appUser==null) {
                throw new UsernameNotFoundException(username + " not found in database");
            } else{
                Collection<
                        GrantedAuthority> authorities = new ArrayList();
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUser.getRole());
                authorities.add(authority);
                User user = new User(appUser.getUsername(), appUser.getPassword(), authorities);
                return user;
            }
    }
}
