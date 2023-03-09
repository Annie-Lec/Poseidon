package com.nnk.springboot.services;

import com.nnk.springboot.domain.AppUser;
import com.nnk.springboot.exceptions.DataNotFoundException;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private static  UserService userService;

    @Mock
    private UserRepository userRepositoryMock;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepositoryMock);
    }

    @Test
    void getAllUsers() {
        //GIVEN
        List<AppUser> appUsers = new ArrayList<>();
        appUsers.add(new AppUser("user1", "Patate01.","fullName1","ROLE_USER"));
        appUsers.add(new AppUser("user2", "Patate02.","fullName2","ROLE_USER"));
        when(userRepositoryMock.findAll()).thenReturn(appUsers);

        //WHEN
        List<AppUser> result = userService.getAllUsers();
        //THEN
        assertThat(result.get(1).getUsername()).isEqualTo("user2");
        verify(userRepositoryMock, times(1)).findAll();
    }

    @Test
    void getUserById() {
        //GIVEN
        int id = 1;
        AppUser obj1 = new AppUser("user1", "Patate01.","fullName1","ROLE_USER");
        Optional<AppUser> opt = Optional.of(obj1);
        when(userRepositoryMock.findById(id)).thenReturn(opt);
        //WHEN
        AppUser result = null;
        try {
            result = userService.getUserById(id);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
        //THEN
        assertThat(result.getUsername()).isEqualTo("user1");
        verify(userRepositoryMock).findById(id);

    }
    @Test
    void getUserById_withException() throws DataNotFoundException {
        //GIVEN
        int idBidon = 99;
        //THEN
        assertThrows(IllegalArgumentException.class, () -> userService.getUserById(idBidon));
    }

    void loadUserByUsername() {
        //GIVEN
        String username = "user1";
        AppUser obj1 = new AppUser("user1", "Patate01.","fullName1","ROLE_USER");

        when(userRepositoryMock.findByUsername(username).get()).thenReturn(obj1);
        //WHEN
        AppUser result =userService.loadUserByUsername("user1");
        //THEN
        assertThat(result.getUsername()).isEqualTo("user1");
        assertThat(result.getFullname()).isEqualTo("fullName1");

    }

    @Test
    void saveUser() {
        //GIVEN
        AppUser toSave = new AppUser("user1", "Patate01.","fullName1","ROLE_USER");
        when(userRepositoryMock.save(toSave)).thenReturn(toSave);
        //WHEN
        AppUser result = userService.saveUser(toSave);
        //THEN
        assertThat(result.getUsername()).isEqualTo("user1");
        verify(userRepositoryMock).save(toSave);

    }

    @Test
    void deleteUser() {

        //WHEN
        userService.deleteUser(1);
        //THEN
        verify(userRepositoryMock).deleteById(1);





    }
}