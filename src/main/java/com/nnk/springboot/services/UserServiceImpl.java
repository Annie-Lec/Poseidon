package com.nnk.springboot.services;

import com.nnk.springboot.domain.AppUser;
import com.nnk.springboot.exceptions.DataNotFoundException;
import com.nnk.springboot.repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Log4j2
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
    public AppUser getUserById(Integer id) throws DataNotFoundException {
        log.info("Service ---> find one User ");
        return userRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("User Id not found in database : " + id));
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
    public void deleteUser(Integer id) {
        log.info("Service ---> delete one User ");
        userRepository.deleteById(id);
    }
}
