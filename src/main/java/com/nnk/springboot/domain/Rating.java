package com.nnk.springboot.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rating")
public class Rating {
    // TODO: Map columns in data table RATING with corresponding java fields
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Integer id;

    @Column(length = 32)
    String moodysRating;
    @Column(length = 32)
    String sandPRating;
    @Column(length = 32)
    String fitchRating;
    @NotNull(message = "order Number is mandatory")
    Integer orderNumber;
}
