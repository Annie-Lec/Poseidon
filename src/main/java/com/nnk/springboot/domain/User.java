package com.nnk.springboot.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
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
