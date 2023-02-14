package com.nnk.springboot.services;

import com.nnk.springboot.domain.AppUser;
import com.nnk.springboot.exceptions.DataNotFoundException;

import java.util.List;

public interface UserService {

    List<AppUser> getAllUsers();
    AppUser getUserById(Integer  id) throws DataNotFoundException;
    AppUser loadUserByUsername(String username);
    AppUser saveUser(AppUser user);
    void deleteUser(Integer  id);

}
