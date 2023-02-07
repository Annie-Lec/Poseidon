package com.nnk.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "trade")
public class Trade {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Integer tradeId;

    @NotBlank(message = "Account is mandatory")
    @Column(length = 32)
    String account;
    @NotBlank(message = "Account is mandatory")
    @Column(length = 32)
    String type;

    @Column(columnDefinition = "DECIMAL(8,1)")
    BigDecimal buyQuantity;
    @Column(columnDefinition = "DECIMAL(8,1)")
    BigDecimal  sellQuantity;
    @Column(columnDefinition = "DECIMAL(8,1)")
    BigDecimal  buyPrice;
    @Column(columnDefinition = "DECIMAL(8,1)")
    BigDecimal  sellPrice;

    @NotBlank(message = "Account is mandatory")
    @Column(length = 32)
    String benchmark;
    @NotNull(message="must not be null - bidListDate is mandatory")
    Timestamp tradeDate;
    @NotBlank(message = "Account is mandatory")
    @Column(length = 32)
    String security;
    @NotBlank(message = "Account is mandatory")
    @Column(length = 32)
    String status;
    @NotBlank(message = "Account is mandatory")
    @Column(length = 32)
    String trader;
    @NotBlank(message = "Account is mandatory")
    @Column(length = 32)
    String book;
    @NotBlank(message = "Account is mandatory")
    @Column(length = 32)
    String creationName;
    @CreationTimestamp
    Timestamp creationDate;

    @NotBlank(message = "Account is mandatory")
    @Column(length = 32)
    String revisionName;
    @UpdateTimestamp
    Timestamp revisionDate;

    @NotBlank(message = "Account is mandatory")
    @Column(length = 32)
    String dealName;
    @NotBlank(message = "Account is mandatory")
    @Column(length = 32)
    String dealType;
    @NotBlank(message = "Account is mandatory")
    @Column(length = 32)
    String sourceListId;
    @NotBlank(message = "Account is mandatory")
    @Column(length = 32)
    String side;

}
