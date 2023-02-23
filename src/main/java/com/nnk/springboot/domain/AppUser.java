package com.nnk.springboot.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "app_user")
public class AppUser {

    public AppUser(String username, String password, String fullname, String role) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.role = role;
    }

   /* public AppUser(String username, String fullname, String role) {
        this.username = username;
        this.fullname = fullname;
        this.role = role;
    }*/


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Username is mandatory")
    private String username;

    @Pattern(regexp="^(?=.*?[A-Z])(?=.*?[0-9])(?=.*?[;.,:/#?!@$%&*-]).{8,}",message="length must be at leat 8 characters : at least one Uppercase and one special character and one numeric character")
    private String password;

    @NotBlank(message = "FullName is mandatory")
    private String fullname;

    @NotBlank(message = "Role is mandatory")
    private String role;

}
