package com.nnk.springboot.services;

import com.nnk.springboot.domain.AppUser;

import java.util.List;

public interface UserService {

    List<AppUser> getAllUsers();
    AppUser getUserById(Integer  id) ;
    AppUser loadUserByUsername(String username);
    AppUser saveUser(AppUser user);

    AppUser updateUser(Integer id, AppUser user) ;
    void deleteUser(Integer  id);

}
