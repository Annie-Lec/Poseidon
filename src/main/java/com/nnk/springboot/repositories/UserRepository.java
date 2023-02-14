package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<AppUser, Integer> {

    public Optional<AppUser> findByUsername(String username);

}
