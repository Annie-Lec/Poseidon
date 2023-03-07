package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.math.BigDecimal;
import java.sql.Timestamp;


@Entity
@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "trade")
public class Trade {

    public Trade(String account, String type,BigDecimal buyQuantity   ) {
        this.account = account;
        this.type = type;
        this.buyQuantity = buyQuantity;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer id;

    @NotBlank(message = "Account is mandatory")
    @Column(length = 30)
    String account;

    @NotBlank(message = "type is mandatory")
    @Column(length = 30)
    String type;

    @DecimalMin(message = "must be greater than 0", value = "0", inclusive = false)
    @NotNull(message = "buy quantity is mandatory")
    @Column(columnDefinition = "DECIMAL(8,1)")
    BigDecimal buyQuantity;
//--------------------------------------
    @Column(columnDefinition = "DECIMAL(8,1)")
    BigDecimal  sellQuantity;
    @Column(columnDefinition = "DECIMAL(8,1)")
    BigDecimal  buyPrice;
    @Column(columnDefinition = "DECIMAL(8,1)")
    BigDecimal  sellPrice;

    Timestamp tradeDate;

    @Column(length = 32)
    String benchmark;
    @Column(length = 32)
    String security;
    @Column(length = 32)
    String status;
    @Column(length = 32)
    String trader;
    @Column(length = 32)
    String book;
    @Column(length = 32)
    String creationName;

    @CreationTimestamp
    Timestamp creationDate;
    @Column(length = 32)
    String revisionName;
    @UpdateTimestamp
    Timestamp revisionDate;


    @Column(length = 32)
    String dealName;

    @Column(length = 32)
    String dealType;

    @Column(length = 32)
    String sourceListId;

    @Column(length = 32)
    String side;

}
