package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer id;
    @NotBlank(message = "Moodys Rating is mandatory")
    @Column(length = 32)
    String moodysRating;
    @NotBlank(message = "SandPs Rating is mandatory")
    @Column(length = 32)
    String sandPRating;
    @NotBlank(message = "Fitch Rating is mandatory")
    @Column(length = 32)
    String fitchRating;
    @DecimalMin(message = "must be greater than 0", value = "0", inclusive = false)
    @NotNull(message = "order Number is mandatory")
    Integer orderNumber;
}
