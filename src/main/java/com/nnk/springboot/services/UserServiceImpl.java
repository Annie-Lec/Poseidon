package com.nnk.springboot.services;

import com.nnk.springboot.domain.AppUser;
import com.nnk.springboot.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService{
    //injection des dépendances par le constructor
    private UserRepository userRepository;

public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
}

    @Override
    public List<AppUser> getAllUsers() {
        log.info("Service ---> find all User ");
        return userRepository.findAll();
    }

    @Override
    public AppUser getUserById(Integer id)  {
        log.info("Service ---> find one User ");
         return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    }

    @Override
    public AppUser loadUserByUsername(String username) {
    AppUser user = userRepository.findByUsername(username).get();

        return user;
    }

    @Override
    public AppUser saveUser(AppUser user) {
        log.info("Service ---> save one User ");
        return userRepository.save(user);
    }

    @Override
    public AppUser updateUser(Integer id, AppUser user)  {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        AppUser userToUpdate = getUserById(id);
        userToUpdate.setRole(user.getRole());
        userToUpdate.setFullname(user.getFullname());
        userToUpdate.setPassword(encoder.encode(user.getPassword()));
        userToUpdate.setRole(user.getRole());
        return userRepository.save(userToUpdate);
    }


    @Override
    public void deleteUser(Integer id) {
        log.info("Service ---> delete one User ");
        userRepository.deleteById(id);
    }
}
