package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "rulename")
public class RuleName {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer id;

    @NotBlank(message = "Name is mandatory")
    @Column(length = 32)
    String name;

    @NotBlank(message = "description is mandatory")
    @Column(length = 32)
    String description;

    @NotBlank(message = "json is mandatory")
    @Column(length = 32)
    String json;

    @NotBlank(message = "template is mandatory")
    @Column(length = 32)
    String template;

    @NotBlank(message = "sqlStr is mandatory")
    @Column(length = 32)
    String sqlStr;

    @NotBlank(message = "sqlPart is mandatory")
    @Column(length = 32)
    String sqlPart;
}
